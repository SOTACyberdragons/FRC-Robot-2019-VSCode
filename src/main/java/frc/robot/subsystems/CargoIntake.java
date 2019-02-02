package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CargoIntake extends Subsystem {


    public Spark cargoMotor;

    @Override
    protected void initDefaultCommand() {
        cargoMotor = new Spark(4);
    }

    public void spinIn() {
        cargoMotor.set(1);
    }

    public void spinOut() {
        cargoMotor.set(-1);
    }

    // Put methods for controlling this subsyst

}