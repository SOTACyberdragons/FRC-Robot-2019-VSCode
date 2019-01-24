package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


/**
 *
 */
public class DifferentialDriveWithXbox extends Command {

    public DifferentialDriveWithXbox() {
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double throttle = 0.8;
        Robot.drivetrain.drive.arcadeDrive(
                Robot.oi.getRightStick().getY() * throttle,
                Robot.oi.getLeftStick().getX() * throttle,
                Robot.oi.getSquaredInput());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
