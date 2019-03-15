package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Climber extends Subsystem {

   // private Spark motor;
    private WPI_TalonSRX talon1; 
    private WPI_TalonSRX talon2;
   
    @Override
    protected void initDefaultCommand() {
       // motor = new Spark(RobotMap.CLIMBER_MOTOR);
        talon1 = new WPI_TalonSRX(RobotMap.CLIMBER_MOTOR1);
        talon2 = new WPI_TalonSRX(RobotMap.CLIMBER_MOTOR2);
    }

    public void set(double speed) {
        talon1.set(speed);
        talon2.set(speed);
       // motor.set(speed);
    }

    public void stop() {
        talon1.set(0);
        talon2.set(0);
    //motor.set(0);
    }
    
}