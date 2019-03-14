/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.HatchPanelFloorIntake;
import frc.robot.subsystems.HatchPanelIntake;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Climber;
import frc.robot.Constants.AutoChoice;
import frc.robot.commands.AutoDoNotMove;
import frc.robot.commands.AutoDriveDistance;
import frc.robot.commands.AutoLeftCenterHatch;
import frc.robot.commands.AutoLeftFartchCargo;
import frc.robot.commands.AutoLeftFartchCargoDouble;
import frc.robot.commands.AutoRightCenterHatch;
import frc.robot.commands.AutoRightFartchCargo;
import frc.robot.commands.AutoRightFartchCargoDouble;
import frc.robot.commands.ZeroArmEncoder;
import frc.robot.subsystems.Arm;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final int IMAGE_WIDTH = 320;
	public static final int IMAGE_HEIGHT = 240;
	public static Preferences prefs;

	public static HatchPanelFloorIntake hatchPanelFloorIntake;
	public static Climber climber;
	public static HatchPanelIntake hatchPanelIntake;
	public static CargoIntake cargoIntake;
	public static Arm arm;
	public static DriveTrain drivetrain;
	public static OI oi;

	private AutoChoice autoChoice;
	private SendableChooser<AutoChoice> chooser;
	private Command autoCommand;

	/* Auto Commands */

	// tests
	private Command autoDriveStraight20Inches;
	private Command autoDriveStraight50Inches;
	
	//auto commands
	private Command autoLeftCenterHatch;
	private Command autoRightCenterHatch;
	private Command autoLeftFartchCargo;
	private Command autoRightFartchCargo;
	private Command autoRightFartchCargoDouble; 
	private Command autoLeftFartchCargoDouble;

	private void initCommands() {
		System.out.println("Initializing commands...");

		// Test
		autoDriveStraight20Inches = new AutoDriveDistance(20, 0, 0);
		autoDriveStraight50Inches = new AutoDriveDistance(50, 0, 0);

		//auto commands
		autoLeftCenterHatch = new AutoLeftCenterHatch();
		autoRightCenterHatch = new AutoRightCenterHatch();
		autoLeftFartchCargo = new AutoLeftFartchCargo();
		autoRightFartchCargo = new AutoRightFartchCargo();
		autoLeftFartchCargoDouble = new AutoLeftFartchCargoDouble();
		autoRightFartchCargoDouble = new AutoRightFartchCargoDouble();

		System.out.println("Done initializing commands");
	}
	@Override
	public void robotInit() {
		hatchPanelFloorIntake = new HatchPanelFloorIntake();
		climber = new Climber();
		hatchPanelIntake = new HatchPanelIntake();
		cargoIntake = new CargoIntake();
		arm = new Arm();
		drivetrain = new DriveTrain();
		oi = new OI();
		
		
		initCommands();


		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMAGE_WIDTH, IMAGE_HEIGHT);


		//Autonomous Chooser
		chooser = new SendableChooser<AutoChoice>();
		chooser.addOption("Drive Straight 20 Inches ", AutoChoice.DRIVE_STRAIGHT_20_INCHES);
		chooser.addOption("Drive Straight 50 Inches", AutoChoice.DRIVE_STRAIGHT_50_INCHES);
		chooser.addOption("Left Center Hatch", AutoChoice.LEFT_CENTER_HATCH);
		chooser.addOption("Right Center Hatch", AutoChoice.RIGHT_CENTER_HATCH );
		chooser.addOption("Left Fartch Cargo", AutoChoice.LEFT_FARTCH_CARGO);
		chooser.addOption("Right Fartch Cargo", AutoChoice.RIGHT_FARTCH_CARGO);
		chooser.addOption("Double Left Fartch Cargo", AutoChoice.RIGHT_FARTCH_CARGO_DOUBLE);
		chooser.addOption("Double Right Fartch Cargo", AutoChoice.LEFT_FARTCH_CARGO_DOUBLE);

		SmartDashboard.putData("Reset Arm Encoder", new ZeroArmEncoder());
		SmartDashboard.putData("Auto mode", chooser);

		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}


	@Override
	public void autonomousInit() {

		
		autoChoice = chooser.getSelected();
		SmartDashboard.putString("Selected Autonomous", autoChoice.toString());

		
		switch(autoChoice) {
			case DO_NOT_MOVE:
				System.out.println("Do not move!");
				autoCommand = new AutoDoNotMove();
				break;
			case DRIVE_STRAIGHT_20_INCHES: 
				System.out.println("It working!");
				autoCommand = autoDriveStraight20Inches;
				break;
			case DRIVE_STRAIGHT_50_INCHES: 
				autoCommand = autoDriveStraight50Inches;
				break;
			case LEFT_CENTER_HATCH:
				autoCommand = autoLeftCenterHatch;
				break;
			case RIGHT_CENTER_HATCH: 
				autoCommand = autoRightCenterHatch;
				break;
			case LEFT_FARTCH_CARGO:
				autoCommand = autoLeftFartchCargo;
				break;
			case RIGHT_FARTCH_CARGO:
				autoCommand = autoRightFartchCargo;
				break;
			case LEFT_FARTCH_CARGO_DOUBLE:
				autoCommand = autoLeftFartchCargoDouble;
				break;
			case RIGHT_FARTCH_CARGO_DOUBLE:
				autoCommand = autoRightFartchCargoDouble;
				break;
			default:
				//will only work on sides
				autoCommand = new AutoDoNotMove();
		}

 
		SmartDashboard.putString("Autonomous Command", autoCommand.getName());
		System.out.println("Starting auto command!");
		autoCommand.start();
		System.out.println("Finished auto command!");
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		//Arm
		SmartDashboard.putNumber("Arm Raw Angle Deg", arm.getRawAngle());
		SmartDashboard.putNumber("ArmFF", arm.getFeedForward());

		SmartDashboard.putNumber("Right Encoder Distance", drivetrain.getRightEncoder());
		SmartDashboard.putNumber("Left Encoder Distance", drivetrain.getLeftEncoder());

	}

	@Override
	public void teleopInit() {

		if (autoCommand != null) {
			autoCommand.cancel();
		}
		drivetrain.resetSensors();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putNumber("Right Encoder Ticks", drivetrain.getRightRawEncoderTicks());
		SmartDashboard.putNumber("Left Encoder Ticks", drivetrain.getLeftRawEncoderTicks());

		SmartDashboard.putNumber("Right Drive", drivetrain.getRightEncoder());
		SmartDashboard.putNumber("Left Drive", drivetrain.getLeftEncoder());

		//SmartDashboard.putNumber("Drive gyro", drivetrain.getHeading());
		SmartDashboard.putNumber("Arm Encoder Angle", arm.getRawAngle());
		SmartDashboard.putBoolean("Break Beam", cargoIntake.getBreakBeam());

		SmartDashboard.putNumber("Arm Encoder Ticks", arm.getLeftRawEncoderTicks());

		//hatch panel intake 
		SmartDashboard.putBoolean("Hatch panel intake is closed", hatchPanelIntake.isClosed());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

	}




}