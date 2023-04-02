package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.RelativeEncoder;

public class ArmSubsystem {
    public CANSparkMax pivot = new CANSparkMax(5, MotorType.kBrushless);
    public CANSparkMax extender = new CANSparkMax(6, MotorType.kBrushless);
    public SparkMaxAbsoluteEncoder pivot_encoder = pivot.getAbsoluteEncoder(com.revrobotics.SparkMaxAbsoluteEncoder.Type.kDutyCycle);
    public SparkMaxPIDController pivot_controller = pivot.getPIDController();
    



    public static ArmFeedforward pivotfeedforward = new ArmFeedforward(0, 1.1, 4.19,0.08);
    public static ElevatorFeedforward extenderfeedforward = new ElevatorFeedforward(0.1, 1.3, 2.3,0.1);

    public ArmSubsystem() {
    }
}