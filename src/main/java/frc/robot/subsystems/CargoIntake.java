package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoIntake extends Subsystem {

    public WPI_TalonSRX cargoMotor;
    private double speed = 1.0;
    private Solenoid ringLight1;
    private Solenoid ringLight2;
    private DigitalInput breakBeam;

    @Override
    protected void initDefaultCommand() {
        cargoMotor = new WPI_TalonSRX(RobotMap.CARGO_MOTOR);
        ringLight1 = new Solenoid(7);
        ringLight2 = new Solenoid(8);
        breakBeam = new DigitalInput(0);
        }

    public void spinIn() {
        cargoMotor.set(speed*0.7);
    }                                                                                                                                                                                                                                                   

    public void spinOut() {
        cargoMotor.set(-speed);
    }

    public void stop() {
        cargoMotor.stopMotor();
    }

    public void lightOn1() {
		ringLight1.set(true);
	}
	
	public void lightOff1() {
		ringLight1.set(false);
    }

    public void lightOn2() {
		ringLight2.set(true);
	}
	
	public void lightOff2() {
		ringLight2.set(false);
    }
    
    public void bothLightsOn() {
        ringLight1.set(true);
        ringLight2.set(true);
    }

    public void bothLightsOff() {
        ringLight1.set(false);
        ringLight2.set(false);
    }

    //we don't have any sensors for this yet
    public boolean hasCargo() {
        return breakBeam.get();
    }


    //775 pro max current is 134 
    public boolean isStalling() {
        boolean check;
        if(cargoMotor.getOutputCurrent() > 100) {
            check = true;
        } else if(cargoMotor.getOutputCurrent() <= 100) {
            check = false;
        } else {
            check = true;
        }
        return check;
    }

    public boolean getBreakBeam() {
        return breakBeam.get();
    }

    public double getTalonOutputVoltage() {
       return cargoMotor.getOutputCurrent();
    }


}