package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ResetDriveSensors extends Command {


    public ResetDriveSensors() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {
        Robot.drivetrain.resetSensors();
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}