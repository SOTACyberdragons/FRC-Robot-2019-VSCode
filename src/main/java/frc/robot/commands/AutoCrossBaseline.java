package frc.robot.commands;

import frc.robot.path.Waypoints.*;
import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCrossBaseline extends CommandGroup {

	double maxSpeed = DriveTrain.MAX_SPEED * 0.6;

	public AutoCrossBaseline() {
		addSequential(new FollowPath(new CrossBaseline(), maxSpeed));
	}
}
