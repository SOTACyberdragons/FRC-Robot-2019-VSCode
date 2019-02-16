package frc.robot.commands;

import frc.robot.path.Waypoints;
import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
public class AutoFartchCargo1 extends CommandGroup {


    public AutoFartchCargo1(double distanceX, double distanceY, double angleDeg) {
        double maxSpeed = DriveTrain.MAX_SPEED * 0.6;

        addParallel(new FollowPath(new Waypoints.Vision(distanceX, distanceY, angleDeg), maxSpeed));

    }
}