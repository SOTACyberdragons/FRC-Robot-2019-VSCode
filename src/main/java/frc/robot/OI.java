package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.CargoIn;
import frc.robot.commands.CargoOut;
import frc.robot.commands.FlashLightForCargo;
import frc.robot.commands.HatchPanelFloorIn;
import frc.robot.commands.HatchPanelFloorOut;
import frc.robot.commands.MoveArmToAngle;
import frc.robot.commands.PistonIn;
import frc.robot.commands.PistonOut;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //public XboxController controller = new XboxController(0);


    JoystickButton pistonOut;
    JoystickButton pistonIn;
    JoystickButton cargoIn;
    JoystickButton cargoOut;
    JoystickButton flashLight;
    JoystickButton floorHatchIn;
    JoystickButton floorHatchOut;
    
    JoystickButton groundPosition;
    JoystickButton backwardsPosition;
    JoystickButton ballInCargoShipPosition;
    JoystickButton ballInRocketPosition;
    JoystickButton ballOutCargoShipPosition;





    // Setting squaredInput to true decreases the sensitivity for tankdrive at lower speeds
	private boolean squaredInput = true;
	
	public Joystick leftStick = new Joystick(0);
    public Joystick rightStick = new Joystick(1);
    public Joystick leftAuxStick = new Joystick(2);
    public Joystick rightAuxStick = new Joystick(3);
    


    public OI() {

        /*
        Set buttons
        */

        //Hatch panel intake
        pistonOut = new JoystickButton(leftStick, ButtonMap.CLOSE_HATCH_PANEL_INTAKE);
        pistonIn = new JoystickButton(leftStick, ButtonMap.OPEN_HATCH_PANEL_INTAKE);

        //Hatch panel floor intake
        floorHatchIn = new JoystickButton(rightAuxStick, ButtonMap.FLOOR_HATCH_PANEL_IN);
        floorHatchOut = new JoystickButton(rightAuxStick, ButtonMap.FLOOR_HATCH_PANEL_OUT);
        
        //Cargo Intake 
        cargoIn = new JoystickButton(leftStick, ButtonMap.INTAKE_CARGO);
        cargoOut = new JoystickButton(rightStick, ButtonMap.RELEASE_CARGO);

        //Arm Positions
        groundPosition = new JoystickButton(leftAuxStick, ButtonMap.GROUND_POSITION);
        backwardsPosition = new JoystickButton(leftAuxStick, ButtonMap.BACKWARDS_POSITION);
        ballInCargoShipPosition = new JoystickButton(leftAuxStick, ButtonMap.BALL_IN_CARGO_SHIP_POSITION);
        ballInRocketPosition = new JoystickButton(leftAuxStick, ButtonMap.BALL_IN_ROCKET_POSITION);
        ballOutCargoShipPosition = new JoystickButton(leftAuxStick, ButtonMap.BALL_OUT_CARGO_SHIP_POSITION);



        //LED
        flashLight = new JoystickButton(rightStick, ButtonMap.FLASH_LIGHT);
       
        /*
        Set commands
        */

        //Hatch panel intake
        pistonOut.whenPressed(new PistonOut());
        pistonIn.whenPressed(new PistonIn());

        //Hatch panel floor intake 
        floorHatchIn.whileHeld(new HatchPanelFloorIn());
        floorHatchOut.whileHeld(new HatchPanelFloorOut());

        //Cargo Intake
        cargoIn.whileHeld(new CargoIn());
        cargoOut.whileHeld(new CargoOut());

        //Arm position
        //change numbers 
        groundPosition.whileHeld(new MoveArmToAngle(148));
        backwardsPosition.whileHeld(new MoveArmToAngle(0));
        ballInCargoShipPosition.whileHeld(new MoveArmToAngle(66)); 
        ballInRocketPosition.whileHeld(new MoveArmToAngle(22));
        ballOutCargoShipPosition.whileHeld(new MoveArmToAngle(91));


        //LED
        flashLight.whileHeld(new FlashLightForCargo());
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

