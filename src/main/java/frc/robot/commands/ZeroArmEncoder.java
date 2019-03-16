package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class ZeroArmEncoder extends Command {

    public ZeroArmEncoder() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.arm);
        System.out.println("Im in");
    }


    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.arm.setEncoder(-90);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        System.out.println("I'm out");
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}