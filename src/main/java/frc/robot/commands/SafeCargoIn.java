package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

/**
 *
 */
public class SafeCargoIn extends CommandGroup {

    public SafeCargoIn() {

    	addParallel(new CargoIn());
    	if(Robot.cargoIntake.isStalling()) {
            addParallel(new FlashLightForCargo());
        }
    }

}