package frc.robot.commands;

import frc.robot.Constants.StartPosition;
import frc.robot.path.Waypoints;
import frc.robot.path.Waypoints.FartchCargo1Left;
import frc.robot.path.Waypoints.FartchCargo1Right;
import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
public class AutoFartchCargo1 extends CommandGroup {

    public AutoFartchCargo1(StartPosition side) {
        
        double maxSpeed = DriveTrain.MAX_SPEED * 0.6;

        switch (side) {
            case LEFT: 
                addParallel(new FollowPath(new FartchCargo1Left(), maxSpeed));
                break;
            case RIGHT:
                addParallel(new FollowPath(new FartchCargo1Right(), maxSpeed));
                break;
            case CENTER:
                break;
            case UNKNOWN:
                break;
            default:
                break;
        }

    }
}