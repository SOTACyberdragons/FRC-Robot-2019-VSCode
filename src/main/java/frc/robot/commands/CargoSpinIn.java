package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoSpinIn extends Command {

    public CargoSpinIn() {
        requires(Robot.cargoIntake);
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.cargoIntake.spinIn();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}