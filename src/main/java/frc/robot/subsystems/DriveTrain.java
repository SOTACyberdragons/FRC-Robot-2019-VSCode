package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DifferentialDriveWithJoysticks;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.pheonix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {

	
	private WPI_TalonSRX leftMotor;
	private WPI_TalonSRX leftMotorFollower;
	private WPI_TalonSRX rightMotor;
	private WPI_TalonSRX rightMotorFollower;
	private SpeedControllerGroup rightMotors;
	private SpeedControllerGroup leftMotors;
	public DifferentialDrive drive;
	
	
	public DriveTrain() {

		leftMotor = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_MOTOR);
		rightMotor = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_MOTOR);
		leftMotorFollower = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_FOLLOW_MOTOR);
		rightMotorFollower = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_FOLLOW_MOTOR);
		rightMotors = new SpeedControllerGroup(rightMotor, rightMotorFollower);
		leftMotors = new SpeedControllerGroup(leftMotor, leftMotorFollower);
		drive = new DifferentialDrive(leftMotors, rightMotors);
	
		
		Robot.initTalon(leftMotor);
		Robot.initTalon(rightMotor);
		Robot.initTalon(rightMotorFollower);
		Robot.initTalon(leftMotorFollower);
		
		//leftMotorFollower.follow(leftMotor);
		//rightMotorFollower.follow(rightMotor);
		
		//rightMotor.setInverted (true);
		
	}
	public void set(ControlMode mode, double leftvalue, double rightvalue) {
		leftMotor.set(mode,leftvalue);
		rightMotor.set(mode,rightvalue);

	}
	
	
	public void initDefaultCommand() {
		setDefaultCommand(new DifferentialDriveWithJoysticks());
	}
}



