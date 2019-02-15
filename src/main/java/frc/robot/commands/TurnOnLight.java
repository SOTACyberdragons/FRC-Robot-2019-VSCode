package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurnOnLight extends Command {


    public TurnOnLight() {
        requires(Robot.cargoIntake);
    }

    protected void initialize() {
        Robot.cargoIntake.lightOn();
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.cargoIntake.lightOff();

    }

    protected void interrupted() {
        end();
    }
}