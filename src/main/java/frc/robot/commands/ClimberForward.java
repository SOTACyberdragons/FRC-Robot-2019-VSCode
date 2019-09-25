package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimberForward extends Command {

    public ClimberForward() {
        requires(Robot.cargoIntake);
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.climber.driveClimber(0.8);
        //System.out.println("Cargo in!!!");
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