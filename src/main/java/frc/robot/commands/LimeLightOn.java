// package frc.robot.commands;

// import edu.wpi.first.wpilibj.command.Command;
// import frc.robot.Robot;
// import oi.limelightvision.limelight.frc.ControlMode.LedMode;

// /**
//  *
//  */
// public class LimeLightOn extends Command {

//     public LimeLightOn() {
//         // Use requires() here to declare subsystem dependencies
//         // eg. requires(chassis);
//         requires(Robot.drivetrain);
//         System.out.println("Im in");
//     }


//     // Called just before this Command runs the first time
//     protected void initialize() {
//         Robot.drivetrain.getLimeLight().setLEDMode(LedMode.kforceOn);
//     }

//     // Called repeatedly when this Command is scheduled to run
//     protected void execute() {
//     }

//     // Make this return true when this Command no longer needs to run execute()
//     protected boolean isFinished() {
//         return false;
//     }

//     // Called once after isFinished returns true
//     protected void end() {
//         Robot.drivetrain.getLimeLight().setLEDMode(LedMode.kforceOff);
//     }

//     // Called when another command which requires one or more of the same
//     // subsystems is scheduled to run
//     protected void interrupted() {
//         end();
//     }
// }