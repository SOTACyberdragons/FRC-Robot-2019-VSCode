package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.CargoIn;
import frc.robot.commands.CargoOut;
import frc.robot.commands.ClimbDown;
import frc.robot.commands.ClimberUp;
import frc.robot.commands.LimeLightAim;
import frc.robot.commands.LimeLightDrive;
import frc.robot.commands.LimeLightDriveAndAim;
//import frc.robot.commands.LimeLightOn;
import frc.robot.commands.MoveArmToAngle;
import frc.robot.commands.PistonIn;
import frc.robot.commands.PistonOut;
import frc.robot.commands.ResetDriveSensors;
import frc.robot.commands.ZeroArmEncoder;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    JoystickButton pistonOut;
    JoystickButton pistonIn;
    JoystickButton cargoIn;
    JoystickButton cargoOut;
    JoystickButton floorHatchIn;
    JoystickButton floorHatchOut;

    JoystickButton zeroArmEncoder;
    JoystickButton resetDriveSensors;
    JoystickButton groundArmEncoder;

    JoystickButton coCargoIn;
    JoystickButton coCargoOut;
    JoystickButton coHatchClose;
    JoystickButton coHatchOpen;

    JoystickButton climbUp;
    JoystickButton climbDown;

    // limelight
    JoystickButton targetAim;
    JoystickButton targetDrive;
    JoystickButton targetDriveAndAim;
    // JoystickButton lightOn;

    // arm positions
    JoystickButton groundPosition;
    JoystickButton backwardsPosition;
    JoystickButton ballInCargoShipPosition;
    JoystickButton ballInRocketPosition;
    JoystickButton ballOutCargoShipPosition;

    // Setting squaredInput to true decreases the sensitivity for tankdrive at lower
    // speeds
    private boolean squaredInput = true;

    public Joystick leftStick = new Joystick(0);
    public Joystick rightStick = new Joystick(1);
    public Joystick leftAuxStick = new Joystick(2);
    public Joystick rightAuxStick = new Joystick(3);

    public OI() {

        /*
         * Set buttons
         */

        // Hatch panel intake
        pistonOut = new JoystickButton(leftStick, ButtonMap.CLOSE_HATCH_PANEL_INTAKE);
        pistonIn = new JoystickButton(leftStick, ButtonMap.OPEN_HATCH_PANEL_INTAKE);

        // Cargo Intake
        cargoIn = new JoystickButton(leftStick, ButtonMap.INTAKE_CARGO);
        cargoOut = new JoystickButton(rightStick, ButtonMap.RELEASE_CARGO);

        coCargoIn = new JoystickButton(leftAuxStick, ButtonMap.CO_CARGO_IN);
        coCargoOut = new JoystickButton(rightAuxStick, ButtonMap.CO_CARGO_OUT);
        coHatchClose = new JoystickButton(rightAuxStick, 2);
        coHatchOpen = new JoystickButton(rightAuxStick, 3);
        // Arm Positions
        // groundPosition = new JoystickButton(leftAuxStick, ButtonMap.GROUND_POSITION);
        // backwardsPosition = new JoystickButton(leftAuxStick,
        // ButtonMap.BACKWARDS_POSITION);
        ballInCargoShipPosition = new JoystickButton(leftAuxStick, ButtonMap.BALL_IN_CARGO_SHIP_POSITION);
        // ballInRocketPosition = new JoystickButton(leftAuxStick,
        // ButtonMap.BALL_IN_ROCKET_POSITION);
        // ballOutCargoShipPosition = new JoystickButton(leftAuxStick,
        // ButtonMap.BALL_OUT_CARGO_SHIP_POSITION);

        zeroArmEncoder = new JoystickButton(leftAuxStick, ButtonMap.ZERO_ARM_ENCODER);
        resetDriveSensors = new JoystickButton(leftStick, ButtonMap.RESET_DRIVE_SENSORS);
        groundArmEncoder = new JoystickButton(leftAuxStick, ButtonMap.GROUND_ARM_ENCODER);

        // limelight
        targetAim = new JoystickButton(leftStick, ButtonMap.TARGET_AIM);
        targetDrive = new JoystickButton(leftStick, ButtonMap.TARGET_DRIVE);
        targetDriveAndAim = new JoystickButton(rightStick, ButtonMap.TARGET_DRIVE_AND_AIM);
        // lightOn = new JoystickButton(rightStick, ButtonMap.LIGHT_ON);

        // climber
        climbUp = new JoystickButton(rightAuxStick, ButtonMap.CLIMB_UP);
        climbDown = new JoystickButton(rightAuxStick, ButtonMap.CLIMB_DOWN);

        /*
         * Set commands
         */

        // Hatch panel intake
        pistonOut.whenPressed(new PistonOut());
        pistonIn.whenPressed(new PistonIn());

        // Cargo Intake
        cargoIn.whileHeld(new CargoIn());
        cargoOut.whileHeld(new CargoOut());

        coCargoIn.whileHeld(new CargoIn());
        coCargoOut.whileHeld(new CargoOut());
        coHatchClose.whenPressed(new PistonIn());
        coHatchOpen.whenPressed(new PistonOut());

        // Arm position
        // up is zero
        // groundPosition.whileHeld(new MoveArmToAngle(90));
        // backwardsPosition.whileHeld(new MoveArmToAngle(-90));
        ballInCargoShipPosition.whileHeld(new MoveArmToAngle(-20));
        // ballInRocketPosition.whileHeld(new MoveArmToAngle(-45));
        // ballOutCargoShipPosition.whileHeld(new MoveArmToAngle(70));

        targetAim.whileHeld(new LimeLightAim());
        targetDrive.whileHeld(new LimeLightDrive());
        targetDriveAndAim.whileHeld(new LimeLightDriveAndAim());
        // lightOn.whileHeld(new LimeLightOn());

        zeroArmEncoder.whenPressed(new ZeroArmEncoder(-90));
        resetDriveSensors.whenPressed(new ResetDriveSensors());
        groundArmEncoder.whenPressed(new ZeroArmEncoder(95));

        // climber
        climbUp.whileHeld(new ClimberUp());
        climbDown.whileHeld(new ClimbDown());

	}
	
	public Joystick getLeftStick() {
		return leftStick;
	}

	public Joystick getRightStick() {
		return rightStick;
	}

    public Joystick getLeftAuxStick() {
		return leftAuxStick;
    }
    
    public Joystick getRightAuxStick() {
        return rightAuxStick;
    }


    public boolean getSquaredInput() {
        return squaredInput;
    }
}

