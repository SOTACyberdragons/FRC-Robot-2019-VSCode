package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.vision.TapePairRecognizer;
import frc.robot.vision.TargetInfo;
import frc.robot.vision.TapePairRecognizer.TapePair;
import frc.robot.LinearAccelerationFilter;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author romandimov
 *
 */

public class VisionLineUp extends Command {

	private PIDController pidAngle;

	private double angleKp;
	private double angleKi;
	private double angleKd;

	private double driveCurve = 0;
	private double driveOutput;

	private LinearAccelerationFilter filter;
	private boolean useAccelerationFiler;

	ArrayList<TapePair> pairs = TapePairRecognizer.recognize(pipeline.filterContoursOutput());
	private boolean wasDetected;

	private boolean waitForVision;

	public VisionLineUp(boolean useAccelerationFiler, boolean waitForVision) {
		requires(Robot.drivetrain);

		this.waitForVision = waitForVision;

		pidAngle = new PIDController(angleKp,
				angleKi,
				angleKd, 
				Robot.drivetrain.getGyro(), 
				new PIDOutput() {
			@Override
			public void pidWrite(double c) {
				driveCurve = c;
			}
		});

		pidAngle.setOutputRange(-1.0, 1.0);

		LiveWindow.addActuator("drivetrain", "Peg Vision Angle Controller", pidAngle);
	}

	protected void initialize() {

		//Get PID values from preferences
		Preferences prefs = Preferences.getInstance();
		angleKp = prefs.getDouble("GetPeg Kp", 0.01);
		angleKi = prefs.getDouble("GetPeg Ki", 0.001);
		angleKd = prefs.getDouble("GetPeg Kd", 0.0);
		driveOutput = prefs.getDouble("GetPeg Speed", 0.5);
		
		Robot.drivetrain.resetSensors();
		Robot.drivetrain.stop();
		pidAngle.reset();
		pidAngle.enable();

		Robot.cargoIntake.bothLightsOn();

		double filterSlopeTime = prefs.getDouble("FilterSlopeTime", 0.2);

		filter = new LinearAccelerationFilter(filterSlopeTime);
		
		System.out.println("\n GetPegWithVision Initialized");
	}

	protected void execute() {
        //updates setpoint only if vision sees object
        int largestTapePairIndex = 0;
        if(pairs.size() > 1) {
            for (int i = 1; i < pairs.size(); i++) {
                double centerX = pairs.get(i).getCenterX();
                TargetInfo targetInfo = new TargetInfo(pairs.get(i));
                TargetInfo previousTargetInfo = new TargetInfo(pairs.get(i-1));
                if(targetInfo.getArea() > previousTargetInfo.getArea()) {
                    largestTapePairIndex = i;
                } else if(targetInfo.getArea() < previousTargetInfo.getArea()) {
                    largestTapePairIndex = i - 1; 
                }
            }
        }

		TargetInfo targetInfo = new TargetInfo(pairs.get(largestTapePairIndex));
		SmartDashboard.putBoolean("Can See With Vision", wasDetected);

		if (targetInfo != null) {
			wasDetected = true;
			pidAngle.setSetpoint(Robot.drivetrain.getHeading() + bBox.angleDeg);
			SmartDashboard.putNumber("PID Vision Setpoint Angle", pidAngle.getSetpoint());
		}

		if (!waitForVision || wasDetected) {
			Robot.drivetrain.drive(driveOutput * (useAccelerationFiler ? filter.output() : 1), driveCurve);
		}
	}

	protected boolean isFinished() {
		//switch off when peg pushes flap
		return Robot.gearIntake.getPegSwitch();
	}

	protected void end() {

		NetworkTable.getTable("vision").putString("model", "gear");

		// Stop PID and the wheels
		pidAngle.disable();
		pidAngle.reset();
		Robot.drivetrain.drive(0, 0);
		Robot.drivetrain.reset();

		Robot.cargoIntake.bothLightsOff();
	}
	
	protected void interrupted() {
		end();
	}
}