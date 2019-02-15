package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FlashLightForCargo extends Command {

    private Timer timer = new Timer();
    public FlashLightForCargo() {
        requires(Robot.cargoIntake);
    }

    protected void initialize() {
        timer.start();
    }

    protected void execute() {
        if((int)(timer.get()*5)/2 == 0) {
            Robot.cargoIntake.lightOn();
        } else{
            Robot.cargoIntake.lightOff();
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.cargoIntake.lightOff();
        timer.stop();
        timer.reset();                                                                                                                                        
    }

    protected void interrupted() {
        end();
    }
}