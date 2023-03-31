package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ControllerSubsystem;

public class ArmExtendCommand {
    public ControllerSubsystem controllerSubsystem;
    public static ArmSubsystem armSubsystem;

    public ArmExtendCommand(ArmSubsystem armSub) {
        armSubsystem = armSub;
        
        controllerSubsystem = new ControllerSubsystem();
        armSubsystem.pivot.set(MathUtil.applyDeadband(controllerSubsystem.operatorLeftJoystickPercent, 0.05));
        armSubsystem.extender.set(MathUtil.applyDeadband(.25*controllerSubsystem.extenderChange, 0.1));
    }
}
