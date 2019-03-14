package frc.robot.commands;

import frc.robot.path.Waypoints;
import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
public class AutoRightCenterHatch extends CommandGroup {


    public AutoRightCenterHatch() {
        double maxSpeed = DriveTrain.MAX_SPEED * 0.6;

        addParallel(new FollowPath(new Waypoints.RightCenterHatch(), maxSpeed));

    }
}