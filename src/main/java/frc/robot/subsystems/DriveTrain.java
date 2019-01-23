package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.Robot;
import org.usfirst.frc.team5700.robot.RobotMap;
import org.usfirst.frc.team5700.robot.commands.DifferentialDriveWithXbox;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 *
 */
public class DriveTrain extends Subsystem {

	
	private TalonSRX leftMotor;
	private TalonSRX leftMotorFollower;
	private TalonSRX rightMotor;
	private TalonSRX rightMotorFollower;

	public DifferentialDrive drive; 
	
	
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
	public void stop() {
		drive.curvatureDrive(0.0, 0.0, true);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DifferentialDriveWithXbox());
	}
}



