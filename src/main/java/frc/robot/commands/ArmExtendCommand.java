package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ControllerSubsystem;

public class ArmExtendCommand {
    public ControllerSubsystem controllerSubsystem;
    public static ArmSubsystem armSubsystem;

    public ArmExtendCommand(ArmSubsystem armSub) {
        armSubsystem = armSub;

        double maxArmTravelTime = 32;
        double maxArmThreshold = (-52.4 + maxArmTravelTime * (-ArmSubsystem.pivotencoder.getVelocity() / 3365));
        
        SmartDashboard.putNumber("angle", ArmSubsystem.pivotencoder.getPosition());

        
        controllerSubsystem = new ControllerSubsystem();

        double pivot = (MathUtil.applyDeadband(controllerSubsystem.operatorLeftJoystickPercent, 0.05)) - ArmSubsystem.pivotfeedforward.calculate(ArmSubsystem.pivotencoder.getPosition(), 1, 2);
        SmartDashboard.putNumber("movement", pivot);
        SmartDashboard.putNumber("velocity", ArmSubsystem.pivotencoder.getVelocity());
        
        SmartDashboard.putNumber("MaxArmThreshold", maxArmThreshold);

        

        if ((ArmSubsystem.pivotencoder.getPosition() < -0.1 && pivot > 0) || (ArmSubsystem.pivotencoder.getPosition() > maxArmThreshold && pivot <= 0)) {
            ArmSubsystem.pivot.set(pivot);
        } else
            ArmSubsystem.pivot.set(-ArmSubsystem.pivotfeedforward.calculate(ArmSubsystem.pivotencoder.getPosition(), 1, 2));
        armSubsystem.extender.set(MathUtil.applyDeadband(.25 * controllerSubsystem.extenderChange, 0.1));
    }
}
