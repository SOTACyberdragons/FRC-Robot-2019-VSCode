
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

//@author Brom
public class HatchPanelFloorIntake extends Subsystem{

	
		
     private WPI_TalonSRX armMotor;
     private WPI_TalonSRX intakeMotor;
     private double intakeSpeed = 0.69420;
     
     public HatchPanelFloorIntake() {
         armMotor = new WPI_TalonSRX(RobotMap.FLOOR_HATCH_INTAKE_MOTOR);
         intakeMotor = new WPI_TalonSRX(RobotMap.FLOOR_HATCH_ARM_MOTOR);
    }
    public void setArmMotor(double speed) {
        armMotor.set(speed);
    }
        public void spinIn() {
        armMotor.set(intakeSpeed);
    }
    public void spinOut() {
        armMotor.set(-intakeSpeed);
    }
    public void stop() {
        armMotor.set(0);
    }


    @Override
    protected void initDefaultCommand() {
    }
}