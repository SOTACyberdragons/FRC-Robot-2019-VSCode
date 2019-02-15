package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurnOnLight extends Command {


    public TurnOnLight() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {
        Robot.drivetrain.lightOn();
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.drivetrain.lightOff();

    }

    protected void interrupted() {
        end();
    }
}