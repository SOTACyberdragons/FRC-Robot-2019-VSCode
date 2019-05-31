package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import oi.limelightvision.limelight.frc.ControlMode.LedMode;
import jaci.pathfinder.followers.DistanceFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class LimeLightAim extends Command {

    private double kpAim = 0.07;
    private double m_moveValue;
    private double m_rotateValue;
    private DistanceFollower left, right;
    private TankModifier modifier;
    private static double kP;
    private static double kI;
    private static double kD;
    private static double kF = 1 / DriveTrain.MAX_SPEED;
    private static double kA = 0;
    private static double angleKP;
    private static double angleKD;
    private Timer timer;
    private double _endToleranceDeg;
    private double _delaySec;

    
    public LimeLightAim() {
      // Use requires() here to declare subsystem dependencies
      // eg. requires(chassis);
      requires(Robot.drivetrain);

      
    }
  
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
      Robot.drivetrain.getLimeLight().setLEDMode(LedMode.kforceOn);
      Robot.drivetrain.resetSensors();
      System.out.print("Sensors reset!");
      kP = 0.45;
      kD = 0.05;
      angleKP = 0.1;
      angleKD = 0.0;
      _endToleranceDeg =20 ;
      _delaySec = 0;

    }
  
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
      //Robot.drivetrain.getLimeLight().setLEDMode(LedMode.kforceOn);
      System.out.println("Aiming!!!!!");
      double tx = Robot.drivetrain.getLimeLight().getdegRotationToTarget();
      boolean targetFound = Robot.drivetrain.getLimeLight().getIsTargetFound();
    
      if(targetFound) {
          m_moveValue = 0;
          m_rotateValue = tx * kpAim;
      } else {
          m_moveValue = 0;
          m_rotateValue = 0 *kpAim;
      }
      SmartDashboard.putNumber("Move Value", m_moveValue);
      SmartDashboard.putNumber("Rotate Value", m_rotateValue);

      
    // Robot.drivetrain.leftMotor.set(controlMode.MotionMagic, );
      Robot.drivetrain.drive(m_moveValue, m_rotateValue);  
      // Robot.drivetrain.rightMotor.set(ControlMode.MotionMagic, 
      //   0, DemandType.AuxPID, m_rotateValue);
      // Robot.drivetrain.leftMotor.set(ControlMode.MotionMagic, m_moveValue,  
      //   DemandType.AuxPID, m_rotateValue);
    }
  
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    //   boolean withinTolerance = (m_rotateValue - _endToleranceDeg) <= Robot.drivetrain.getHeading() 
    //   && Robot.drivetrain.getHeading() <= (m_rotateValue + _endToleranceDeg);
    // return withinTolerance;
    return false;
    }
  
    // Called once after isFinished returns true
    @Override
    protected void end() {
    Robot.drivetrain.drive(0,0);
    System.out.println("Done aiming!");
    Robot.drivetrain.getLimeLight().setLEDMode(LedMode.kforceOff);
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
      end();
    }
  }
  
