package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbDown extends Command {

    public ClimbDown() {
        requires(Robot.climber);
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.climber.climbDown();
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