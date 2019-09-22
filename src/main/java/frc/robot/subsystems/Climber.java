package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

// author: Josh 
public class Climber extends Subsystem {

    private WPI_TalonSRX rightTalon;
    private WPI_TalonSRX leftTalon;
    private WPI_TalonSRX centerTalon;

    private double upSpeed = 1.0;
    private double downSpeed = -1.0;
    private double forwardSpeed = 0.3;
    private double backwardSpeed = -0.3;

    
   
    @Override
    protected void initDefaultCommand() {
        rightTalon = new WPI_TalonSRX(RobotMap.RIGHT_CLIMBER_MOTOR);
        leftTalon = new WPI_TalonSRX(RobotMap.LEFT_CLIMBER_MOTOR);
        centerTalon = new WPI_TalonSRX(RobotMap.CENTER_CLIMBER_MOTOR);
        leftTalon.follow(rightTalon);
    }
    public void climbUp() {
        rightTalon.set(upSpeed);
    }

   public void driveClimber(double speed) {
       centerTalon.set(speed);
   }
    public void climbDown() {
        rightTalon.set(downSpeed);
    }

    public void stopCLimb() {
        rightTalon.set(0);
    }
    public void stopwheels() {
        centerTalon.set(0); 
    }

}   