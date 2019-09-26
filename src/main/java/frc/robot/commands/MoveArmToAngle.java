package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveArmToAngle extends Command {

    private double angleDeg;
    private double delaySec;
    private double endToleranceDeg;

    public static int numRunning = 0;
    
    public MoveArmToAngle(double angle) {
        requires(Robot.arm);
        angleDeg = angle;
        delaySec = 0;
        endToleranceDeg = 10;
    }

    public MoveArmToAngle(double angle, double delaySecond) {
        requires(Robot.arm);
        angleDeg = angle;
        delaySec = delaySecond;
        endToleranceDeg = 1;
    }

    protected void initialize() {
        System.out.println("Move arm to angle started");
    		Timer.delay(delaySec);
    }

    protected void execute() {
        numRunning += 1;
        Robot.arm.moveArmToAngle(angleDeg);
    }

    protected boolean isFinished() {
        boolean withinTolerance = (angleDeg - endToleranceDeg) <= Robot.arm.getRawAngle() 
        && Robot.arm.getRawAngle() <= (angleDeg + endToleranceDeg);
        System.out.println("Arm at angle!!!!!!");
        return withinTolerance;
    }

    protected void end() {
        numRunning -= 1;
        Robot.arm.setTalons(0);
    }

    protected void interrupted() {
        end();
    }
}