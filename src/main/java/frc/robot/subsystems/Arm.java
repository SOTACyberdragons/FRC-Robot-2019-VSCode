package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Instrum;
import frc.robot.Robot;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.MoveArmWithJoystick;


/**
 *
 */
public class Arm extends Subsystem {

	//Constants
	public final static double REDUCTION_TO_ENCODER = 100; 
	public final static double TICKS_PER_DEG = 8080 /180; // (Constants.VERSA_ENCODER_TPR * REDUCTION_TO_ENCODER) / 360;
	public final static double ENCODER_MAX_SPEED = 33000; //ticks per 100 ms


	
	//Motor controllers 
	private WPI_TalonSRX rightArmTalon;
	private WPI_TalonSRX leftArmTalon;

	Preferences prefs;

	//Maximum nominal output, when arm is horizontal to ground
	public double wHatchMaxNominalOutput; 
	public double wCargoMaxNominalOutput; 
	public double noGamePieceMaxNominalOutput;
	
	
	public Arm() {

		rightArmTalon = new WPI_TalonSRX(RobotMap.RIGHT_ARM_MOTOR);
		rightArmTalon.setName("Arm", "Right Arm Talon");
		leftArmTalon = new WPI_TalonSRX(RobotMap.LEFT_ARM_MOTOR);
		leftArmTalon.setName("Arm", "Right Arm Talon");

		initArmTalon(leftArmTalon);
		initArmTalon(rightArmTalon);
		
	}

	public void initArmTalon(WPI_TalonSRX talon) {
		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PID_LOOP_IDX, Constants.TIMEOUT_MS);
		setEncoder(0); //@TODO figure out what this should be
		talon.setSensorPhase(true);
		talon.setInverted(true);
	
		/* Set relevant frame periods to be at least as fast as periodic rate */
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.TIMEOUT_MS);
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.TIMEOUT_MS);
	
		/* set the peak and nominal outputs */
		talon.configNominalOutputForward(0, Constants.TIMEOUT_MS);
		talon.configNominalOutputReverse(0, Constants.TIMEOUT_MS);
		talon.configPeakOutputForward(1, Constants.TIMEOUT_MS);
		talon.configPeakOutputReverse(-1, Constants.TIMEOUT_MS);
	
		/* set closed loop gains in slot 0 - see documentation */
		talon.selectProfileSlot(Constants.SLOT_IDX, Constants.PID_LOOP_IDX);
		talon.config_kF(0, Constants.TALON_MAX_OUTPUT/ENCODER_MAX_SPEED, Constants.TIMEOUT_MS);
		talon.config_kP(0, 0.1, Constants.TIMEOUT_MS);
		talon.config_kI(0, 0, Constants.TIMEOUT_MS);
		talon.config_kD(0, 0, Constants.TIMEOUT_MS);
		
		/* set acceleration and cruise velocity - see documentation */
		talon.configMotionCruiseVelocity(25000 , Constants.TIMEOUT_MS);
		talon.configMotionAcceleration(20000, Constants.TIMEOUT_MS);
	}

	public double getFeedForward() {
		prefs = Preferences.getInstance();
		wHatchMaxNominalOutput = prefs.getDouble("armWHatchOut", 0.15); // set these values
		prefs.putDouble("FFwHatch", wHatchMaxNominalOutput);
		wCargoMaxNominalOutput = prefs.getDouble("armWCargoOut", 0.15);
		prefs.putDouble("FFwCargo", wCargoMaxNominalOutput);
		noGamePieceMaxNominalOutput = prefs.getDouble("armNoCargoOrHatchOut", 0.25);
		prefs.putDouble("FFempty", noGamePieceMaxNominalOutput);
		
		double maxNominalOutput;
		if(Robot.hatchPanelIntake.hasHatch() && !Robot.cargoIntake.hasCargo()) {
			maxNominalOutput = wHatchMaxNominalOutput;
		} else if(Robot.cargoIntake.hasCargo() && !Robot.hatchPanelIntake.hasHatch()) {
			maxNominalOutput = wCargoMaxNominalOutput;
		} else if(!Robot.cargoIntake.hasCargo() && !Robot.hatchPanelIntake.hasHatch()) {
			maxNominalOutput = noGamePieceMaxNominalOutput;
		} else {
			maxNominalOutput = noGamePieceMaxNominalOutput;
		}
		//Feed Forward Logic
		return Math.sin(Math.toRadians(getRawAngle())) * maxNominalOutput;
	}

	public double getRawAngle() {
		//average degrees because two talons control the arm
		return (leftArmTalon.getSelectedSensorPosition(0) / TICKS_PER_DEG
		+ rightArmTalon.getSelectedSensorPosition(0) / TICKS_PER_DEG) / 2;

	}

	public void zeroEncoder() {
		leftArmTalon.setSelectedSensorPosition(0, Constants.PID_LOOP_IDX, Constants.TIMEOUT_MS);
		rightArmTalon.setSelectedSensorPosition(0, Constants.PID_LOOP_IDX, Constants.TIMEOUT_MS);
	}
	
	public double getLeftRawEncoderTicks() {
		return leftArmTalon.getSelectedSensorPosition(0);
	}

	public double getRightRawEncoderTicks() {
		return rightArmTalon.getSelectedSensorPosition(0);
	}

	public void setEncoder(double angle) {
		leftArmTalon.setSelectedSensorPosition((int)(angle * TICKS_PER_DEG), Constants.PID_LOOP_IDX, Constants.TIMEOUT_MS);
		rightArmTalon.setSelectedSensorPosition((int)(angle * TICKS_PER_DEG), Constants.PID_LOOP_IDX, Constants.TIMEOUT_MS);
	}

	public void moveArmWithJoystick(double stickValue) {
		StringBuilder sb = new StringBuilder();
		setTalons(stickValue + getFeedForward());
		Instrum.Process(leftArmTalon, sb);
		Instrum.Process(rightArmTalon, sb);
	}

	//current
	public double getLeftCurrent() {
		return leftArmTalon.getOutputCurrent();	
	}

	public double getRightCurrent() {
		return rightArmTalon.getOutputCurrent();
	}
	
	public void setTalons(double output) {
		leftArmTalon.set(ControlMode.PercentOutput, output);
		rightArmTalon.set(ControlMode.PercentOutput, output);
	}
	public void initDefaultCommand() {
		setDefaultCommand(new MoveArmWithJoystick());
	}

	public void moveArmToAngle(double angle) {
		leftArmTalon.set(ControlMode.MotionMagic, angle * TICKS_PER_DEG);
		rightArmTalon.set(ControlMode.MotionMagic, angle * TICKS_PER_DEG);

	}

}
