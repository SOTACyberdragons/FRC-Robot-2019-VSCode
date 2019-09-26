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
        Robot.climber.driveClimber(0.5);
        //System.out.println("Cargo in!!!");
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.climber.stopWheels();
    }

    protected void interrupted() {
        end();
    }
}