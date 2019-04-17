package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import oi.limelightvision.limelight.frc.ControlMode.LedMode;


/**
 *
 */
public class LimeLightAim extends Command {

    private double kpAim = 0.035;
    private double m_moveValue;
    private double m_rotateValue;
    
    public LimeLightAim() {
      // Use requires() here to declare subsystem dependencies
      // eg. requires(chassis);
      requires(Robot.drivetrain);
    }
  
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
      //Robot.drivetrain.getLimeLight().setLEDMode(LedMode.kforceOn);
    }
  
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    //Robot.drivetrain.getLimeLight().setLEDMode(LedMode.kforceOn);
    System.out.println("Aiming!!!!!");
    double tx = Robot.drivetrain.getLimeLight().getdegRotationToTarget();
    boolean targetFound = Robot.drivetrain.getLimeLight().getIsTargetFound();
  
    if(targetFound){
        m_moveValue = 0;
        m_rotateValue = tx * kpAim;
    }else{
        m_moveValue = 0;
        m_rotateValue = 0 *kpAim;
    }
  
      Robot.drivetrain.drive(m_moveValue, m_rotateValue);
      
    }
  
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
      return false;
    }
  
    // Called once after isFinished returns true
    @Override
    protected void end() {
      Robot.drivetrain.drive(0,0);
      System.out.println("Done aiming!");
      Robot.drivetrain.getLimeLight().setLEDMode(LedMode.kforceOn);
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
      end();
    }
  }
  
