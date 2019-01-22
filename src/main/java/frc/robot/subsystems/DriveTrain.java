package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.DifferentialDriveWithXbox;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 *
 */
public class DriveTrain extends Subsystem {

	
	private Spark leftMotor;
	private Spark rightMotor;
	public DifferentialDrive drive; 
	
	
	public DriveTrain() {

		leftMotor = new Spark(RobotMap.LEFT_DRIVE_MOTOR);
		rightMotor = new Spark(RobotMap.RIGHT_DRIVE_MOTOR);
		drive = new DifferentialDrive(leftMotor, rightMotor);
	}

	public void stop() {
		drive.curvatureDrive(0.0, 0.0, true);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DifferentialDriveWithXbox());
	}
}



