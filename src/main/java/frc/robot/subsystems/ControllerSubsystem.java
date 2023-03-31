package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;

public class ControllerSubsystem {
  //create a Joystick object
  public Joystick driver = new Joystick(0);
  public Joystick operator = new Joystick(1);
  public double leftWheel = 0;
  public double rightWheel = 0;
  public double fwdDiv = 1;
  public double spinDiv = 2;
  public double leftJoystickPercent;
  public double rightJoystickPercent;
  public double rightTriggerPercent;
  public double leftTriggerPercent;
  public double operatorLeftJoystickPercent;
  public double operatorRightJoystickPercent;
  public double extenderChange;


  public ControllerSubsystem() {
        rightTriggerPercent = -operator.getRawAxis(3);
        leftTriggerPercent = -operator.getRawAxis(4);
        operatorLeftJoystickPercent = operator.getRawAxis(1);
        operatorRightJoystickPercent = operator.getRawAxis(5);
        extenderChange = rightTriggerPercent - leftTriggerPercent;

        // intake / outtake claw
        if (operator.getRawButton(6)) {  // outake
            leftWheel = -.75;
            rightWheel = -.75;
        }
        if (operator.getRawButton(5)) { // intake
            leftWheel = .75;
            rightWheel = .75;
        }
        
        // adjusting drivetrain speeds
        if (driver.getRawButton(5)) { // forward speed divisor
            fwdDiv = 2;
        }
        if (driver.getRawButton(6)) { // spin speed divisor
            spinDiv = 0.5;
        }

        // these two have to be after the button if statements
        leftJoystickPercent = -driver.getRawAxis(1) / fwdDiv;
        rightJoystickPercent = -driver.getRawAxis(4) / spinDiv;

        if (operator.getRawButtonPressed(14)) {
            HandSubsystem.solenoidToggle();
        }
    }
}
