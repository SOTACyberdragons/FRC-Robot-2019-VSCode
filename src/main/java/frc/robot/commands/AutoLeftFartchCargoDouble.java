package frc.robot.commands;

import frc.robot.path.Waypoints;
import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
public class AutoLeftFartchCargoDouble extends CommandGroup {


    public AutoLeftFartchCargoDouble() {
        double maxSpeed = DriveTrain.MAX_SPEED * 0.6;

        addParallel(new FollowPath(new Waypoints.LeftFartchCargo(), maxSpeed));
        addSequential(new FollowPath(new Waypoints.LeftFartchCargoRetrieval(), maxSpeed));
        addSequential(new FollowPath(new Waypoints.LeftFartchCargo2(), maxSpeed));

    }
}