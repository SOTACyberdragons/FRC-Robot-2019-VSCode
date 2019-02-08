package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.CargoIn;
import frc.robot.commands.CargoOut;
import frc.robot.commands.PistonIn;
import frc.robot.commands.PistonOut;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {


    JoystickButton pistonOut;
    JoystickButton pistonIn;
    JoystickButton cargoIn;
    JoystickButton cargoOut;
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
        
        //Cargo Intake 
        cargoIn = new JoystickButton(leftStick, ButtonMap.INTAKE_CARGO);
        cargoOut = new JoystickButton(rightStick, ButtonMap.RELEASE_CARGO);
       
        /*
        Set commands
        */

        //Hatch panel intake
        pistonOut.whenPressed(new PistonOut());
        pistonIn.whenPressed(new PistonIn());

        //Cargo Intake
        cargoIn.whileHeld(new CargoIn());
        cargoOut.whileHeld(new CargoOut());
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
    
    public Joystick getRIghtAuxStick() {
        return rightAuxStick;
    }


    public boolean getSquaredInput() {
        return squaredInput;
    }
}

