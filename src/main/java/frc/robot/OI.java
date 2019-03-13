package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.HatchPanelFloorIn;
import frc.robot.commands.HatchPanelFloorOut;
import frc.robot.commands.ResetDriveSensors;
import frc.robot.commands.TurnOnLight;
import frc.robot.subsystems.DriveTrain;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //public XboxController controller = new XboxController(0);

    JoystickButton floorHatchIn;
    JoystickButton floorHatchOut;
    JoystickButton lightOn;
    JoystickButton resetDriveSensors;
    





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

        //hatch panel
        floorHatchIn = new JoystickButton(rightAuxStick, ButtonMap.FLOOR_HATCH_IN);
        floorHatchOut = new JoystickButton(rightAuxStick, ButtonMap.FLOOR_HATCH_OUT);

        //LED
        lightOn = new JoystickButton(rightStick, ButtonMap.FLASH_LIGHT);
        resetDriveSensors = new JoystickButton(leftStick, ButtonMap.RESET_DRIVE_SENSORS);
       
        /*
        Set commands
        */

        //hatch panel
        floorHatchIn.whileHeld(new HatchPanelFloorIn());
        floorHatchOut.whileHeld(new HatchPanelFloorOut());


        //LED
        lightOn.whileHeld(new TurnOnLight());
        resetDriveSensors.whenPressed(new ResetDriveSensors());
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

