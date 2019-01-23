/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.PistonIn;
import frc.robot.commands.PistonOut;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public XboxController controller = new XboxController(0);
    JoystickButton pistonOut;
    JoystickButton pistonIn;
    // Setting squaredInput to true decreases the sensitivity for tankdrive at lower speeds
    private boolean squaredInput = true;


    public OI() {
        pistonOut = new JoystickButton(controller, 1);
        pistonOut.whenPressed(new PistonOut());
        pistonIn = new JoystickButton(controller, 2);
        pistonIn.whenPressed(new PistonIn());
    }


    public XboxController.Thumbstick getLeftStick() {
        return controller.leftStick;
    }

    public XboxController.Thumbstick getRightStick() {
        return controller.rightStick;
    }

    public boolean getSquaredInput() {
        return squaredInput;
    }
}

