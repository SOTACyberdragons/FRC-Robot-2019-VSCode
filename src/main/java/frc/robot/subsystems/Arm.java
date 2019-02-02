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
public class Arm extends Subsystem {

	
	private TalonSRX armMotor;

	
	
	public Arm() {

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
