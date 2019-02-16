package frc.robot.commands;

import frc.robot.Robot;


import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDoNotMove extends Command {

    public AutoDoNotMove() {
    	requires(Robot.drivetrain);
    }
    
	protected void initialize() {

	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		Robot.drivetrain.stop();
	}

	protected void interrupted() {
		end();
	}
}