package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DifferentialDriveWithJoysticks;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {

	
	private WPI_TalonSRX leftMotor;
	private WPI_TalonSRX leftMotorFollower;
	private WPI_TalonSRX rightMotor;
	private WPI_TalonSRX rightMotorFollower;
	public DifferentialDrive drive;
	
	
	public DriveTrain() {

		leftMotor = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_MOTOR);
		rightMotor = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_MOTOR);
		leftMotorFollower = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_FOLLOW_MOTOR);
		rightMotorFollower = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_FOLLOW_MOTOR);
		drive = new DifferentialDrive(leftMotor, rightMotor);
	
		
		Robot.initTalon(leftMotor);
		Robot.initTalon(rightMotor);
		Robot.initTalon(rightMotorFollower);
		Robot.initTalon(leftMotorFollower);
		
		leftMotorFollower.follow(leftMotor);
		rightMotorFollower.follow(rightMotor);
	}
	
	
	public void initDefaultCommand() {
		setDefaultCommand(new DifferentialDriveWithJoysticks());
	}
}



