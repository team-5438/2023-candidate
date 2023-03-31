package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase{
    public CANSparkMax left_back_drive = new CANSparkMax(1, MotorType.kBrushless);
    public CANSparkMax left_front_drive = new CANSparkMax(2, MotorType.kBrushless);
    public CANSparkMax right_back_drive = new CANSparkMax(3, MotorType.kBrushless);
    public CANSparkMax right_front_drive = new CANSparkMax(4, MotorType.kBrushless);    

    public MotorControllerGroup left  = new MotorControllerGroup(left_back_drive, left_front_drive);
    public MotorControllerGroup right = new MotorControllerGroup(right_back_drive, right_front_drive);

    public ControllerSubsystem controllerSubsystem;


    public DrivetrainSubsystem() {
        //invert the "right" side of the drivetrain
        right_back_drive.setInverted(true);
        right_front_drive.setInverted(true);
        
        //follow the "leaders" of the right and the left side to minimize the amount of code we need to write
    }
}
