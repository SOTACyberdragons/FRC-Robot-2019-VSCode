package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.Robot;
import org.usfirst.frc.team5700.robot.RobotMap;
import org.usfirst.frc.team5700.robot.commands.DifferentialDriveWithJoysticks;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Arm extends Subsystem {

	
	private TalonSRX armMotor;

	
	
	public DriveTrain() {

		armMotor = new TalonSRX(RobotMap.ARM_MOTOR);

		Robot.initTalon(armMotor);
		
	}
	public void set(ControlMode mode, double value) {
		armMotor.set(mode, value);
	}
	public void stop() {
		armMotor.set(mode, 0);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new MoveArmWithXBox());
	}
}
