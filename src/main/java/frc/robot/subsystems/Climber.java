package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Climber extends Subsystem {

    private Spark motor;

    public Climber() {
        motor = new Spark(RobotMap.CLIMBER_MOTOR);
    }

    public void set(double speed) {
        motor.set(speed);
    }

    public void stop() {
        motor.set(0);
    }

    public void initDefaultCommand() {
    }
    
}