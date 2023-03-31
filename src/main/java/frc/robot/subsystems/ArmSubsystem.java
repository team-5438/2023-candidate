package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmSubsystem {
    public CANSparkMax pivot = new CANSparkMax(5, MotorType.kBrushless);
    public CANSparkMax extender = new CANSparkMax(6, MotorType.kBrushless);
}