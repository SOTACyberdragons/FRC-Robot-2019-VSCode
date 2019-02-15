package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoIn extends Command {

    public CargoIn() {
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
      Robot.cargoIntake.stop();
    }

    protected void interrupted() {
        end();
    }
}