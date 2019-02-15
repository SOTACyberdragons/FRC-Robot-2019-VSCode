package frc.robot.commands;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

public class FindTarget extends Command {

    @Override
    protected void initialize() {
        CameraServer.getInstance().startAutomaticCapture();
    }

    @Override
    protected void execute() {
        super.execute();
    }

    @Override
    protected void end() {
        super.end();
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    public double getY() {
        return 0;
    }

    public double getX() {
        return 0;
    }

    public double getAngle() {
        return 0;
    }
}
