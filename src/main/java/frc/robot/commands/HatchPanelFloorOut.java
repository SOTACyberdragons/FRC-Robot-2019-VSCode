package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchPanelFloorOut extends Command {


    public HatchPanelFloorOut() {
        requires(Robot.hatchPanelFloorIntake);
    }

    protected void initialize() {
        Robot.hatchPanelFloorIntake.spinOut();
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