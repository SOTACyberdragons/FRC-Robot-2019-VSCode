package frc.robot.commands;
import frc.robot.path.Waypoints.DriveDistance;
import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
public class AutoDriveDistance extends CommandGroup {

    public AutoDriveDistance(double distanceX, double distanceY, double angleDeg) {
        double maxSpeed = DriveTrain.MAX_SPEED * 0.6;
        System.out.println("In drive distance command!");
        addParallel(new FollowPath(new DriveDistance(distanceX, distanceY, angleDeg), maxSpeed));

    }
}
