package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SafeCargoIn extends Command {

    public SafeCargoIn() {
        requires(Robot.cargoIntake);
    }

    protected void initialize() {
    }

    protected void execute() {
        if(!Robot.cargoIntake.isStalling()) {
            Robot.cargoIntake.spinIn();
        } else if(Robot.cargoIntake.isStalling()) {
            Robot.cargoIntake.stop();
        } else {
            Robot.cargoIntake.spinIn();
        }
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