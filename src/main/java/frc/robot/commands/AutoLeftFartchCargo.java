package frc.robot.commands;

import frc.robot.path.Waypoints;
import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
public class AutoLeftFartchCargo extends CommandGroup {


    public AutoLeftFartchCargo() {
        double maxSpeed = DriveTrain.MAX_SPEED * 0.6;

        addParallel(new FollowPath(new Waypoints.LeftFartchCargo(), maxSpeed));

    }
}