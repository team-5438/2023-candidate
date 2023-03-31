package frc.robot.subsystems;

import com8kauailabs.navx.frc.AHRS;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import java.util.function.Supplier;
import java.util.function.BiConsumer;
import java.nio.file.Path;
import java.nio.file.Paths;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.controller.DifferentialDriveWheelVoltages;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.trajectory.*;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Encoder;

import frc.robot.*;

import com.pathplanner.lib.commands.PPRamseteCommand;
import com.pathplanner.lib.PathPlannerTrajectory;


public class DrivetrainSubsystem {
    public CANSparkMax left_back_drive = new CANSparkMax(1, MotorType.kBrushless);
    public CANSparkMax left_front_drive = new CANSparkMax(2, MotorType.kBrushless);
    public CANSparkMax right_back_drive = new CANSparkMax(3, MotorType.kBrushless);
    public CANSparkMax right_front_drive = new CANSparkMax(4, MotorType.kBrushless);    
    public ControllerSubsystem controllerSubsystem;
    public DifferentialDrive drive;

    public RelativeEncoder leftEncoder;
    public RelativeEncoder rightEncoder; 
    public SimpleMotorFeedforward motorFeedforward;

    private AHRS navx;
    private Rotation2d navxOffset;
    private SerialPort serial;
    private RamseteController controller;

    private double leftVoltages;
    private double rightVoltages;

    public DrivetrainSubsystem() {
        drive = new DifferentialDrive(left_front_drive, right_front_drive);
        leftEncoder = left_front_drive.getEncoder();
        rightEncoder = right_front_drive.getEncoder();
      
        motorFeedforward = new SimpleMotorFeedforward(1, 4, 6);
        // SimpleMotorFeedforward Inputs:
        // 1. minimum voltage motors need to move
        // 2. voltage needed to move motor at certain velocity
        // 3. voltage needed to move motor at certain acceleration

        double maxAccel = motorFeedforward.maxAchievableAcceleration(Constants.MAX_VOLTAGE, 4);
        motorFeedforward.calculate(4, 6);

        Port USBA1 = SerialPort.Port.kUSB1;

        navx = new AHRS(USBA1);

        navxOffset = new Rotation2d();
        navx.reset();
        navx.calibrate();
      
        //invert the "right" side of the drivetrain
        right_back_drive.setInverted(true);
        right_front_drive.setInverted(true);
        
        //follow the "leaders" of the right and the left side to minimize the amount of code we need to write
        left_back_drive.follow(left_front_drive);
        right_back_drive.follow(right_front_drive);
    }

    public void arcadeDrive(double fwd, double rotation)
    {
      drive.arcdeDrive(fwd, rotation);
    }

    public Supplier<Pose2d> getPose()
    {
      return () -> new DifferentialDriveOdometry(
        null, 
        left_front_drive.getEncoder().getPosition(),
        right_front_drive.getEncoder().getPosition()).getPoseMeters();
    }

  public Supplier<DifferentialDriveWheelSpeeds> WheelSpeedsSupplier()
  {
    return () -> new DifferentialDriveWheelSpeeds(
      left_front_drive.getEncoder().getVelocity(),
      right_front_drive.getEncoder().getVelocity()
    );
  }

  public double getPitch()
  {
    return navx.getPitch();
  }

  public Trajectory convertPPtoWPI()
  {}

  BiConsumer<Double, Double> voltageConsumer = (leftVoltage, rightVoltage) -> 
  {
    double forwardSpeed = (leftVoltage + rightVoltage) / (2 * Constants.MAX_VOLTAGE);
    double rotationSpeed = (leftVoltage - rightVoltage) / (2 * Constants.MAX_VOLTAGE);
  }

  public Command followTrajectory(PathPlannerTrajectory path)
  {
    return new PPRamseteCommand(
      path,
      getPose(),
      new RamseteController(),
      motorFeedforward,
      Constants.kDriveKinematics,
      WheelSpeedsSupplier(),
      new PIDController(),
      new PIDController(),
    )
  }
}
