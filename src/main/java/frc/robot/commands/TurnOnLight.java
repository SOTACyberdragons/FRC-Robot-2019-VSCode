package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurnOnLight extends Command {


    public TurnOnLight() {
        requires(Robot.cargoIntake);
    }

    protected void initialize() {
        if(Robot.arm.getRawAngle() < 180) {
            Robot.cargoIntake.lightOn1();
        } else if(Robot.arm.getRawAngle() > 180) {
            Robot.cargoIntake.lightOn2();
        } else {
            Robot.cargoIntake.bothLightsOn();
        }
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.cargoIntake.bothLightsOff();

    }

    protected void interrupted() {
        end();
    }
}