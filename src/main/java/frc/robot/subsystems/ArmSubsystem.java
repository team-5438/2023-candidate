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

    // Function to convert encoder counts to radians
        double encoderCountsToRadians(double encoderCounts) {
            double angleRadians = (encoderCounts / 8192) * (2 * Math.PI);
            return angleRadians;
        }

    public static ArmFeedforward pivotfeedforward = new ArmFeedforward(0, 1.1, 4.19,0.08);
    public static ElevatorFeedforward extenderfeedforward = new ElevatorFeedforward(0.1, 1.3, 2.3,0.1);

    public ArmSubsystem() {
        pivot_controller.setP(0.1); // change all of these
        pivot_controller.setI(0.0001);
        pivot_controller.setD(0.01);
        pivot_controller.setFF(-pivotfeedforward.calculate(pivot_encoder.getPosition(), 0)); // change this
        pivot_controller.setOutputRange(-0.5,0.5);
    }
}