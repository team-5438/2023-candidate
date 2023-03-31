package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import frc.robot.subsystems.*;

public class ClawCommand {
    public ControllerSubsystem controllerSubsystem;

    public ClawCommand(HandSubsystem handSubsystem) {
        controllerSubsystem = new ControllerSubsystem();

        handSubsystem.leftIntake.set(controllerSubsystem.leftWheel);
        handSubsystem.rightIntake.set(controllerSubsystem.rightWheel);
        handSubsystem.rightIntake.setInverted(true);
        handSubsystem.RoboComp.enableCompressorAnalog(80, 110);
        
        HandSubsystem.wrist.set(MathUtil.applyDeadband(controllerSubsystem.operatorRightJoystickPercent, 0.05));
    }
}
