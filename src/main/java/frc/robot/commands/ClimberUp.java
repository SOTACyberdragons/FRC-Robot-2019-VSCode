package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimberUp extends Command {

    public ClimberUp() {
        requires(Robot.climber);
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.climber.climbUp();
        //System.out.println("Cargo in!!!");
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.climber.stopCLimb();
    }

    protected void interrupted() {
        end();
    }
}