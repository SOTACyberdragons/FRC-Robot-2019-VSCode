package frc.robot.commands;

import java.io.File;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.path.Waypoints;
import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.DistanceFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class FollowPathTimeTest extends Command {

    private static double kP;
    private static double kI;
    private static double kD;
    private static double kF = 1 / DriveTrain.MAX_SPEED;
    private static double kA = 0;
    private static double angleKP;
    private static double angleKD;

    private Trajectory trajectory;
    private TankModifier modifier;
    private DistanceFollower left;
    private DistanceFollower right;
    private DriveTrain drive;
    private Preferences prefs;
    private Timer timer;
    private double angleError;
    private double lastAngleError;
    Trajectory.Config config;

    public FollowPathTimeTest() {
        super();
        requires(Robot.drivetrain);

        System.out.println("Initializing Follow Path Time Test");

        timer = new Timer();
        prefs = Robot.prefs;
        drive = Robot.drivetrain;
        int maxSpeed = 1;

        config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
                10, //Trajectory.Config.SAMPLES_FAST,
                Constants.CYCLE_SEC,
                maxSpeed,
                DriveTrain.MAX_ACCEL,
                DriveTrain.MAX_JERK);
        timer.reset();
        timer.start();
        double calc_start = timer.get();
        int x = 100;
        int y = 200;
        int angle = 1;

        trajectory = Pathfinder.generate(new Waypoint[]
            {new Waypoint(0, 0, 0),
            new Waypoint(x, y, angle)}, 
            config);
        modifier = new TankModifier(trajectory).modify(DriveTrain.WHEELBASE_WIDTH);

        left = new DistanceFollower(modifier.getLeftTrajectory());
        right = new DistanceFollower(modifier.getRightTrajectory());
        double time_taken = timer.get() - calc_start;
        System.out.println("Time to calculate trajectory: " + time_taken);

        //		File trajectoryCsv = new File(Constants.PATHFINDER_DIR + waypoints.getClass().getSimpleName() + ".csv");
        //		Pathfinder.writeToCSV(trajectoryCsv, trajectory);

    }

    protected void initialize() {
        System.out.println("In Follow Path Time Test init");
        timer.reset();
        timer.start();
        double calc_start = timer.get();
        int x = 100;
        int y = 200;
        int angle = 1;

        trajectory = Pathfinder.generate(new Waypoint[]
            {new Waypoint(0, 0, 0),
            new Waypoint(x, y, angle)}, 
            config);
        modifier = new TankModifier(trajectory).modify(DriveTrain.WHEELBASE_WIDTH);

        left = new DistanceFollower(modifier.getLeftTrajectory());
        right = new DistanceFollower(modifier.getRightTrajectory());
        double time_taken = timer.get() - calc_start;
        System.out.println("Time to calculate trajectory: " + time_taken);
    }

    protected void execute() {
        System.out.println("In Follow Path Execute");
        double calc_start = timer.get();
        int x = 100;
        int y = 200;
        int angle = 1;

        trajectory = Pathfinder.generate(new Waypoint[]
            {new Waypoint(0, 0, 0),
            new Waypoint(x, y, angle)}, 
            config);
        modifier = new TankModifier(trajectory).modify(DriveTrain.WHEELBASE_WIDTH);

        left = new DistanceFollower(modifier.getLeftTrajectory());
        right = new DistanceFollower(modifier.getRightTrajectory());
        double time_taken = timer.get() - calc_start;
        System.out.println("Time to calculate trajectory: " + time_taken);

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        System.out.println("Ended Follow Path Time Test");
        timer.stop();
    }

    protected void interrupted() {
        end();
    }
}