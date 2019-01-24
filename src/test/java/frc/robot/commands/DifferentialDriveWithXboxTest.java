package frc.robot.commands;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.XboxController;
import frc.robot.XboxController.Thumbstick;
import frc.robot.subsystems.DriveTrain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DifferentialDriveWithXboxTest {

    private DifferentialDriveWithXbox command;
    private Thumbstick stick;

    @Before
    public void setUp() throws Exception {
        Robot.drivetrain = mock(DriveTrain.class);
        Robot.drivetrain.drive = mock(DifferentialDrive.class);
        Robot.oi = mock(OI.class);
        when(Robot.oi.getSquaredInput()).thenReturn(true);

        stick = mock(Thumbstick.class);
        when(Robot.oi.getLeftStick()).thenReturn(stick);
        when(Robot.oi.getRightStick()).thenReturn(stick);

        command = new DifferentialDriveWithXbox();
    }

    // @Test
    // public void positiveValues() {
    //     when(stick.getX()).thenReturn(0.4);
    //     when(stick.getY()).thenReturn(0.5);

    //     command.execute();

    //     verify(Robot.drivetrain.drive).curvatureDrive(0.8 * 0.4, 0.8 * 0.5, true);
    // }

    // @Test
    // public void negativeValues() {
    //     when(stick.getX()).thenReturn(-0.4);
    //     when(stick.getY()).thenReturn(-0.5);

    //     command.execute();

    //     verify(Robot.drivetrain.drive).curvatureDrive(0.8 * -0.4, 0.8 * -0.5, true);
    // }

    // @Test
    // public void zero() {
    //     when(stick.getX()).thenReturn(0.0);
    //     when(stick.getY()).thenReturn(0.0);

    //     command.execute();

    //     verify(Robot.drivetrain.drive).curvatureDrive(0.0, 0.0, true);
    // }
}
