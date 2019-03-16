package frc.robot.commands;

import frc.robot.path.Waypoints;
import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
public class AutoLeftCenterHatch extends CommandGroup {


    public AutoLeftCenterHatch() {
        double maxSpeed = DriveTrain.MAX_SPEED * 0.4;

        addParallel(new FollowPath(new Waypoints.LeftCenterHatch(), maxSpeed), 10);
        addParallel(new MoveArmToAngle(90), 10);
        //addSequential(new PistonOut());

    }
}