package frc.robot.commands;

import frc.robot.path.Waypoints;

import edu.wpi.first.wpilibj.command.CommandGroup;
public class VisionGo extends CommandGroup {

    public VisionGo() {

        addSequential(new TurnOnLight());
        var findTarget = new FindTarget();
        addParallel(findTarget, 0.5);
        addSequential(new FollowPath(new Waypoints.Vision(findTarget.getY(), findTarget.getX(), findTarget.getAngle()), 0.5));

    }
}
