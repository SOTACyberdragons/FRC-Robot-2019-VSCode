package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.MoveArmWithJoystick;


/**
 *
 */
public class Arm extends Subsystem {

	
	private WPI_TalonSRX rightArmMotor;
	private WPI_TalonSRX leftArmMotor;

	
	
	public Arm() {

		rightArmMotor = new WPI_TalonSRX(RobotMap.RIGHT_ARM_MOTOR);
		leftArmMotor = new WPI_TalonSRX(RobotMap.LEFT_ARM_MOTOR);

		Robot.initTalon(leftArmMotor);
		Robot.initTalon(rightArmMotor);
		
	}
	public void set(ControlMode mode, double value) {
		rightArmMotor.set(mode, value);
		leftArmMotor.set(mode, value);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new MoveArmWithJoystick());
	}
}
