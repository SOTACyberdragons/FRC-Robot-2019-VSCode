package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoIntake extends Subsystem {


    public WPI_TalonSRX cargoMotor;

    @Override
    protected void initDefaultCommand() {
        cargoMotor = new WPI_TalonSRX(RobotMap.CARGO_MOTOR);
    }

    public void spinIn() {
        cargoMotor.set(0.5);
    }

    public void spinOut() {
        cargoMotor.set(-0.5);
    }

    public void stop() {
        cargoMotor.set(0);
    }
    //we don't have any sensors for this yet
    public boolean hasCargo() {
        return false;
    }

    // Put methods for controlling this subsyst

}