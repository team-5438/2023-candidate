package frc.robot.commands;

import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.math.MathUtil;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ControllerSubsystem;

public class ArmExtendCommand {
    public ControllerSubsystem controllerSubsystem;
    public static ArmSubsystem armSubsystem;

    public ArmExtendCommand(ArmSubsystem armSub) {
        armSubsystem = armSub;
        double maxPositionChange = .1;
        double positionChange = MathUtil.applyDeadband(controllerSubsystem.operatorLeftJoystickPercent, 0.05) * maxPositionChange;
        double setpoint = armSubsystem.pivot_encoder.getPosition() + positionChange; // change this maybe
        controllerSubsystem = new ControllerSubsystem();
        //if(controllerSubsystem.operatorLeftJoystickPercent < 0.2 && controllerSubsystem.operatorLeftJoystickPercent > -0.2){
            
          //  armSubsystem.pivot_controller.setReference(.7, ControlType.kPosition);
        //}
        //else{
            //armSubsystem.pivot.set(MathUtil.applyDeadband(controllerSubsystem.operatorLeftJoystickPercent, 0.05));
            armSubsystem.extender.set(MathUtil.applyDeadband(.25*controllerSubsystem.extenderChange, 0.1));
            armSubsystem.pivot_controller.setReference(setpoint, ControlType.kPosition);
        //}
    }
}
