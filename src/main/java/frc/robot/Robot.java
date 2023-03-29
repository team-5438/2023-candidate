// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.util.IdentityHashMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;


  /*
  write code to create the following motors:
  - leftMotor2 (1)
  - leftMotor3 (2)
  - rightMotor1 (3)
  - rightMotor2 (4)
  - rightMotor3 (5)
  */

  private CANSparkMax left_back_drive = new CANSparkMax(1, MotorType.kBrushless);
  private CANSparkMax left_front_drive = new CANSparkMax(2, MotorType.kBrushless);
  private CANSparkMax right_back_drive = new CANSparkMax(3, MotorType.kBrushless);
  private CANSparkMax right_front_drive = new CANSparkMax(4, MotorType.kBrushless);
  private CANSparkMax wrist = new CANSparkMax(0, MotorType.kBrushless);
  private CANSparkMax leftIntake = new CANSparkMax(0, MotorType.kBrushless);
  private CANSparkMax rightIntake = new CANSparkMax(0, MotorType.kBrushless);
  private CANSparkMax pivot = new CANSparkMax(0, MotorType.kBrushless);
  private CANSparkMax extender = new CANSparkMax(0, MotorType.kBrushless);
  DifferentialDrive m_robotDrive = new DifferentialDrive(left_front_drive, left_back_drive);
  

  //create a Joystick object
  private Joystick driver = new Joystick(0);
  private Joystick operator = new Joystick(1);

  //create intake motor

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    //invert the "right" side of the drivetrain
    right_back_drive.setInverted(true);
    right_front_drive.setInverted(true);
    rightIntake.setInverted(true);

    //follow the "leaders" of the right and the left side to minimize the amount of code we need to write
    left_back_drive.follow(left_front_drive);
    right_back_drive.follow(right_front_drive);
    rightIntake.follow(leftIntake);
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
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

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
    //percent output: ranges from -1 to 1
      double leftJoystickPercent = -driver.getRawAxis(1);
      double rightJoystickPercent = -driver.getRawAxis(4);
      double rightTriggerPercent = -operator.getRawAxis(3);
      double leftTriggerPercent = -operator.getRawAxis(6);
      double operatorLeftJoystickPercent = -operator.getRawAxis(1);
      double operatorRightJoystickPercent = -operator.getRawAxis(4);

      //left_front_drive.set(MathUtil.applyDeadband(leftJoystickPercent, .05));
      //right_front_drive.set(rightJoystickPercent);
      m_robotDrive.arcadeDrive(leftJoystickPercent, rightJoystickPercent);
      extender.set(MathUtil.applyDeadband(rightTriggerPercent, 0.05));
      extender.set(MathUtil.applyDeadband(leftTriggerPercent, 0.05));
      wrist.set(MathUtil.applyDeadband(operatorLeftJoystickPercent, 0.05));
      pivot.set(MathUtil.applyDeadband(operatorRightJoystickPercent, 0.05));
      while (operator.getRawButton(5)){
        leftIntake.set(.5);
      }
      while (operator.getRawButton(4)){
        leftIntake.set(-.5);
      }
      while (driver.getRawButton(5)) {
        m_robotDrive.arcadeDrive(leftJoystickPercent, rightJoystickPercent);
      }
      while (driver.getRawButton(4)){
        m_robotDrive.arcadeDrive(leftJoystickPercent, rightJoystickPercent);
      }

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