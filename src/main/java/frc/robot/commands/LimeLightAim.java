package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

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

    private double kpAim = 0.035;
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
    private Timer timer2;


    
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
      timer.reset();
      timer.start();
      kP = 0.45;
      kD = 0.05;
      angleKP = 0.1;
      angleKD = 0.0;

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
   
    // left = new DistanceFollower(modifier.getLeftTrajectory());
    // right = new DistanceFollower(modifier.getRightTrajectory());
    
		// double gyroHeading = - Robot.drivetrain.getHeading();    // gyro is clockwise, pathfinder counter-clockwise
		// double desiredHeading = m_rotateValue;  // Should also be in degrees
  
    
    // double lastAngleError = 0; 
		// double angleError = (desiredHeading - gyroHeading);
		// double angleErrorChange = lastAngleError - angleError;
	  // lastAngleError = angleError;
		// SmartDashboard.putNumber("AngleError", angleError);
		// SmartDashboard.putNumber("AngleErrorChange", angleErrorChange);
    
    // double leftMotorOutput = left.calculate(Robot.drivetrain.getLeftEncoder()) - 
		// 		(angleKP * angleError - angleKD * angleErrorChange);
		// double rightMotorOutput = right.calculate(Robot.drivetrain.getRightEncoder() + 
		// 		(angleKP * angleError - angleKD * angleErrorChange));
		
		// SmartDashboard.putNumber("Pathfinder.leftMotorOutput", leftMotorOutput);
		// SmartDashboard.putNumber("Pathfinder.rightMotorOutput", rightMotorOutput);

		//		System.out.println("Pathfinder at " + timer.get() + ", output: " + leftMotorOutput);
    //Robot.drivetrain.leftMotor.set(ControlMode.Position, rightMotorOutput);
   // Robot.drivetrain.leftMotor.set(controlMode.MotionMagic, )
    //Robot.drivetrain.drive1.tankDrive(leftMotorOutput, rightMotorOutput);
    Robot.drivetrain.drive(m_moveValue, m_rotateValue);
    if (timer.get() >= 2) {
    Robot.drivetrain.getLimeLight().setLEDMode(LedMode.kforceOff);
    }   
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
  //    Robot.drivetrain.getLimeLight().setLEDMode(LedMode.kforceOff);
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
      end();
    }
  }
  
