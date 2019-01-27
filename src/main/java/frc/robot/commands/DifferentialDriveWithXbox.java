package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DifferentialDriveWithXbox extends Command {

    public DifferentialDriveWithXbox() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    }

    protected void execute() {
        double throttle = 0.8;
        Robot.drivetrain.drive.arcadeDrive(
                Robot.oi.getRightStick().getY() * throttle,
                Robot.oi.getLeftStick().getX() * throttle,
                Robot.oi.getSquaredInput());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
