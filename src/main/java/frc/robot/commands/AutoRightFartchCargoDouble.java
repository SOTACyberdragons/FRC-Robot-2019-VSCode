package frc.robot.commands;

import frc.robot.path.Waypoints;
import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
public class AutoRightFartchCargoDouble extends CommandGroup {


    public AutoRightFartchCargoDouble() {
        double maxSpeed = DriveTrain.MAX_SPEED * 0.6;

        addParallel(new FollowPath(new Waypoints.RightFartchCargo(), maxSpeed));
        addSequential(new FollowPath(new Waypoints.RightFartchCargoRetrieval(), maxSpeed));
        addSequential(new FollowPath(new Waypoints.RightFartchCargo2(), maxSpeed));

    }
}