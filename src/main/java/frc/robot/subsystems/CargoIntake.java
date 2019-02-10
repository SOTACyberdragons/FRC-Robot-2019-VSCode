package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoIntake extends Subsystem {


    public WPI_TalonSRX cargoMotor;
    private double speed = 0.5;
    private Solenoid ringLight;

    @Override
    protected void initDefaultCommand() {
        cargoMotor = new WPI_TalonSRX(RobotMap.CARGO_MOTOR);
        ringLight = new Solenoid(3);
    }

    public void spinIn() {
        cargoMotor.set(speed);
    }

    public void spinOut() {
        cargoMotor.set(-speed);
    }

    public void stop() {
        cargoMotor.stopMotor();
    }

    public void lightOn() {
		ringLight.set(true);
	}
	
	public void lightOff() {
		ringLight.set(false);
	}

    //we don't have any sensors for this yet
    public boolean hasCargo() {
        return false;
    }

    // Put methods for controlling this subsyst

}