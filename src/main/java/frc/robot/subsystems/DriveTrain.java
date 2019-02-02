package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DifferentialDriveWithJoysticks;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {

	
	private TalonSRX leftMotor;
	private TalonSRX leftMotorFollower;
	private TalonSRX rightMotor;
	private TalonSRX rightMotorFollower;

	
	
	public DriveTrain() {

		leftMotor = new TalonSRX(RobotMap.LEFT_DRIVE_MOTOR);
		rightMotor = new TalonSRX(RobotMap.RIGHT_DRIVE_MOTOR);
		leftMotorFollower = new TalonSRX(RobotMap.LEFT_DRIVE_FOLLOW_MOTOR);
		rightMotorFollower = new TalonSRX(RobotMap.RIGHT_DRIVE_FOLLOW_MOTOR);
	
		
		Robot.initTalon(leftMotor);
		Robot.initTalon(rightMotor);
		Robot.initTalon(rightMotorFollower);
		Robot.initTalon(leftMotorFollower);
		
		leftMotorFollower.follow(leftMotor);
		rightMotorFollower.follow(rightMotor);
		
		rightMotor.setInverted (true);
		
	}
	public void set(ControlMode mode, double leftvalue, double rightvalue) {
		leftMotor.set(mode,leftvalue);
		rightMotor.set(mode,leftvalue);

	}
	
	
	public void initDefaultCommand() {
		setDefaultCommand(new DifferentialDriveWithJoysticks());
	}
}



