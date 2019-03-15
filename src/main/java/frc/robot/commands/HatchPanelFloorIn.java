package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchPanelFloorIn extends Command {


    public HatchPanelFloorIn() {
        requires(Robot.hatchPanelFloorIntake);
    }

    protected void initialize() {
        Robot.hatchPanelFloorIntake.spinIn();
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.hatchPanelFloorIntake.stop();

    }

    protected void interrupted() {
        end();
    }
}