package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Prototype extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Compressor compressor = new Compressor();
    private Solenoid piston;

    public Prototype() {
        super();
        piston = new Solenoid(0);
        piston.set(true);
    }

    public void out() {
        piston.set(true);
    }

    public void in() {
        piston.set(false);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}