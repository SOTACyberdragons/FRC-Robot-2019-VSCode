package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoOut extends Command {

    public CargoOut() {
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
       Robot.cargoIntake.stop();
    }

    protected void interrupted() {
        end();
    }
}