package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Instrum;
import frc.robot.RobotMap;
import frc.robot.commands.DriveClimberWithJoystick;

// author: Josh 
public class Climber extends Subsystem {

    private WPI_TalonSRX rightTalon;
    private WPI_TalonSRX leftTalon;
    private WPI_TalonSRX centerTalon;

    private double upSpeed = 1.0;
    private double downSpeed = -1.0;
    private double forwardSpeed = 0.3;
    private double backwardSpeed = -0.3;
    private double throttle = 0.5;

    public Climber() {
        rightTalon = new WPI_TalonSRX(RobotMap.RIGHT_CLIMBER_MOTOR);
        leftTalon = new WPI_TalonSRX(RobotMap.LEFT_CLIMBER_MOTOR);
        centerTalon = new WPI_TalonSRX(RobotMap.CENTER_CLIMBER_MOTOR);
        initClimberTalon(centerTalon);
    }
   
    @Override
    protected void initDefaultCommand() {  
        setDefaultCommand(new DriveClimberWithJoystick());
    }


    public void initClimberTalon(WPI_TalonSRX talon) {
        talon.configNominalOutputForward(0, Constants.TIMEOUT_MS);
        talon.configNominalOutputReverse(0, Constants.TIMEOUT_MS);
        talon.configPeakOutputForward(1, Constants.TIMEOUT_MS);
        talon.configPeakOutputReverse(-1, Constants.TIMEOUT_MS);

        talon.configMotionCruiseVelocity(25000 , Constants.TIMEOUT_MS);
		talon.configMotionAcceleration(20000, Constants.TIMEOUT_MS);
    }

    public void climbUp() {
        rightTalon.set(-upSpeed*throttle);
        leftTalon.set(upSpeed*throttle);
    }

   public void driveClimber(double speed) {
    centerTalon.set(ControlMode.PercentOutput, speed);

   }

    public void climbDown() {
        rightTalon.set(-downSpeed*throttle);
        leftTalon.set(downSpeed*throttle);

    }

    public void stopCLimb() {
        rightTalon.set(0);
        leftTalon.set(0);
    }
    public void stopWheels() {
        centerTalon.set(0); 
    }
    
    public boolean isStalling() {
        if(rightTalon.getOutputCurrent() > 100){
            boolean check = true;
    
        }else if(rightTalon.getOutputCurrent() <= 100) {
            boolean check = false;
        } else {
            boolean check = true;
        }
        return true;
    }
}   