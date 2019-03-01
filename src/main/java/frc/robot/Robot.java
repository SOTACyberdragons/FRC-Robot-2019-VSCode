/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.Constants.AutoChoice;
import frc.robot.commands.AutoDoNotMove;
import frc.robot.commands.AutoDriveDistance;
import frc.robot.subsystems.DriveTrain;
import frc.robot.vision.GripPipelineContour;
import frc.robot.vision.TapePairRecognizer;
import frc.robot.vision.TargetInfo;
import frc.robot.vision.TapePairRecognizer.TapePair;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	public static final int IMAGE_WIDTH = 320;
	public static final int IMAGE_HEIGHT = 240;
	private Command autonomousCommand;
	public static Preferences prefs;

	public static DriveTrain drivetrain;
	public static OI oi;

	private AutoChoice autoChoice;
	private SendableChooser<AutoChoice> chooser;
	private Command autoCommand;

	private static boolean isRecording;

	/* Auto Commands */

	// tests
	private Command autoDriveStraight20Inches;
	private Command autoDriveStraight50Inches;
	private final Object imgLock = new Object();
	private double centerX;
	private TargetInfo targetInfo;

	private void initCommands() {
		System.out.println("Initializing commands...");

		// Test
		autoDriveStraight20Inches = new AutoDriveDistance(20, 0, 0);
		autoDriveStraight50Inches = new AutoDriveDistance(50, 0, 0);

		System.out.println("Done initializing commands");

		SmartDashboard.putData("autoDriveStraight20Inches", autoDriveStraight20Inches);
		SmartDashboard.putData("autoDriveStraight50Inches", autoDriveStraight50Inches);

	}

	@Override
	public void robotInit() {

		drivetrain = new DriveTrain();
		oi = new OI();

		initCommands();

		// Autonomous Chooser
		chooser = new SendableChooser<AutoChoice>();
		chooser.addObject("Drive Straight 20 Inches ", AutoChoice.DRIVE_STRAIGHT_20_INCHES);
		chooser.addDefault("Drive Straight 50 Inches", AutoChoice.DRIVE_STRAIGHT_50_INCHES);

		SmartDashboard.putData("Auto mode", chooser);

		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMAGE_WIDTH, IMAGE_HEIGHT);
		//@TODO: camera exposure and change from dashboard
		//camera.setExposureManual(value);

		VisionThread visionThread = new VisionThread(camera, new GripPipelineContour(), pipeline -> {
            if (!pipeline.filterContoursOutput().isEmpty()) {
				TapePair pair = TapePairRecognizer.recognize(pipeline.filterContoursOutput()).get(0);
				//only proceed if pair is not empty (ArrayList.empty)
				synchronized (imgLock) {
				centerX = pair.getCenterX();
				System.out.println(centerX);
				targetInfo = new TargetInfo(pair);
				System.out.println(targetInfo);
                }
            }
        });
        visionThread.start();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}


	@Override
	public void autonomousInit() {
		
		autoChoice = chooser.getSelected();
		SmartDashboard.putString("Selected Autonomous", autoChoice.toString());

		switch(autoChoice) {
			case DO_NOT_MOVE:
				autoCommand = new AutoDoNotMove();
				break;
			case DRIVE_STRAIGHT_20_INCHES: 
				autoCommand = autoDriveStraight20Inches;
				break;
			case DRIVE_STRAIGHT_50_INCHES: 
				autoCommand = autoDriveStraight50Inches;
			default:
				//will only work on sides
				autoCommand = new AutoDoNotMove();
		}
 
		SmartDashboard.putString("Autonomous Command", autoCommand.getName());
		System.out.println("Starting auto command!");
		autoCommand.start();
		System.out.println("Finished auto command!");

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		

		SmartDashboard.putNumber("Right Encoder Distance", drivetrain.getRightEncoder());
		SmartDashboard.putNumber("Left Encoder Distance", drivetrain.getLeftEncoder());


		Scheduler.getInstance().run();
		double centerX;
		synchronized (imgLock) {
			centerX = this.centerX;
			System.out.println("Inside autonomous: " + targetInfo);
			//get heading, distance, and plane angle here
		}
		double turn = centerX - (IMAGE_WIDTH / 2);
		System.out.println("Turn is: " + turn);
		SmartDashboard.putNumber("Turn is: ", turn);
	}

	@Override
	public void teleopInit() {

		if (autoCommand != null) {
			autoCommand.cancel();
		}
		//drivetrain.resetSensors();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		double centerX;
		synchronized (imgLock) {
			centerX = this.centerX;
		}
		double turn = centerX - (IMAGE_WIDTH / 2);
		System.out.println("Turn is: " + turn);
		SmartDashboard.putNumber("Turn is: ", turn);

	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

	}


	public static void initTalon(TalonSRX talon) {
		talon.setNeutralMode(NeutralMode.Coast);
		talon.neutralOutput();
		talon.setSensorPhase(false);
		talon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
		talon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
		talon.configNominalOutputForward(0.0,0);
		talon.configNominalOutputReverse(0.0,0);
		talon.configClosedloopRamp(0.5,0);
	}


}