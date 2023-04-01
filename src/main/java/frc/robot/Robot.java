// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Timer;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ArmExtendCommand;
import frc.robot.commands.ClawCommand;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  public ArmSubsystem armSubsystem;
  public ControllerSubsystem controllerSubsystem;
  public DrivetrainSubsystem drivetrainSubsystem;
  public ClawCommand inOutTake;
  public ArmExtendCommand armExtendCommand;
  DifferentialDrive m_robotDrive;
  public HandSubsystem handSubsystem;

  //private RobotContainer m_robotContainer;

  edu.wpi.first.wpilibj.Timer time = new edu.wpi.first.wpilibj.Timer();

  /*
  write code to create the following motors:
  - leftMotor2 (1)
  - leftMotor3 (2)
  - rightMotor1 (3)
  - rightMotor2 (4)
  - rightMotor3 (5)
  */

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    handSubsystem = new HandSubsystem();
    drivetrainSubsystem = new DrivetrainSubsystem();
    armSubsystem = new ArmSubsystem();
    m_robotDrive = new DifferentialDrive(drivetrainSubsystem.left_front_drive, drivetrainSubsystem.right_front_drive);
    controllerSubsystem = new ControllerSubsystem();
    armExtendCommand = new ArmExtendCommand(armSubsystem);
    inOutTake = new ClawCommand(handSubsystem);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    
    CommandScheduler.getInstance().run();
   
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    

    time.reset();
    time.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

    if (time.get() >= 0 && time.get() <= 2.2){
      armSubsystem.pivot.set(-0.3);
    }
    
    if (time.get() > 2.3 && time.get() <= 4.3){
      armSubsystem.pivot.set(-0.05);
      armSubsystem.extender.set(0.15);
    } 

    if(time.get() > 4.3 && time.get() <= 6){
      armSubsystem.extender.set(-0.1);
      handSubsystem.wrist.set(0.1);
    }
    if(time.get() > 6 && time.get() <= 8){
      handSubsystem.wrist.set(0);
      handSubsystem.leftIntake.set(-.25);
      handSubsystem.rightIntake.set(-.25);
    }
    if(time.get() > 8 && time.get() <= 10){
      handSubsystem.wrist.set(-0.05);
      handSubsystem.leftIntake.set(0);
      handSubsystem.rightIntake.set(0);
      armSubsystem.pivot.set(0.1);
    }

    if (time.get() >= 11.9 && time.get() <= 15){
        m_robotDrive.arcadeDrive(-0.5, 0);
    }
    else{
      m_robotDrive.arcadeDrive(0, 0);
    }
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    controllerSubsystem = new ControllerSubsystem();
    armExtendCommand = new ArmExtendCommand(armSubsystem);
    inOutTake = new ClawCommand(handSubsystem);

    m_robotDrive.arcadeDrive(controllerSubsystem.leftJoystickPercent, controllerSubsystem.rightJoystickPercent);
    ArmSubsystem.pivotfeedforward.calculate(20, 30, 40);
    ArmSubsystem.extenderfeedforward.calculate(20, 30);

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }
  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}