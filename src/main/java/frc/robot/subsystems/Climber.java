package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Climber extends Subsystem {

    private TalonSRX rightMotor, leftMotor;
    public Climber() {
        rightMotor = new TalonSRX(RobotMap.RIGHT_CLIMBER_MOTOR);
        rightMotor = new TalonSRX(RobotMap.RIGHT_CLIMBER_MOTOR);
    }

    public void set(double speed) {
        rightMotor.set(ControlMode.PercentOutput, speed);
        leftMotor.set(ControlMode.PercentOutput, speed);
    }

    public void stop() {
        rightMotor.set(ControlMode.PercentOutput, 0);
        leftMotor.set(ControlMode.PercentOutput, 0);
    }

    public void initDefaultCommand() {
    }
    
}
   