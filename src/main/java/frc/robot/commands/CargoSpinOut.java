package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoSpinOut extends Command {

    public CargoSpinOut() {
        requires(Robot.cargoIntake);
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.cargoIntake.spinOut();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}