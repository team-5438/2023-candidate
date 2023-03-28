package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;

public class DriveTrain {

  private static DriveTrain instance;

  private CANSparkMax[] leftSparks;
  private CANSparkMax[] rightSparks;

  private double leftDemand;
  private double rightDemand;

  private double lastUpdated;

  private DriveTrain() {
    this.leftSparks = new CANSparkMax[Constants.Drivetrain.LEFT_SPARKS.length];
    this.rightSparks = new CANSparkMax[Constants.Drivetrain.RIGHT_SPARKS.length];

    // setup all sparks
    for (int i = 0; i < this.leftSparks.length; i++) {
      this.leftSparks[i] = Constants.Drivetrain.LEFT_SPARKS[i].createSpark();

      if (i > 0) {
        this.leftSparks[i].follow(this.leftSparks[0]);
      }
    }

    for (int i = 0; i < this.rightSparks.length; i++) {
      this.rightSparks[i] = Constants.Drivetrain.RIGHT_SPARKS[i].createSpark();

      if (i > 0) {
        this.rightSparks[i].follow(this.rightSparks[0]);
      }
    }
    //

    if (Timer.getFPGATimestamp() - this.lastUpdated > 0.50) {
      this.leftDemand = 0;
      this.rightDemand = 0;
    }

    this.leftSparks[0].set(this.leftDemand);
    this.rightSparks[0].set(this.rightDemand);
  }

  public void set(double left, double right) {
    this.leftDemand = left;
    this.rightDemand = right;
    this.lastUpdated = Timer.getFPGATimestamp();
  }

  public static synchronized void initialize() {
    if (DriveTrain.instance == null) {
      DriveTrain.instance = new DriveTrain();
    }
  }

  public static DriveTrain getInstance() {
    return DriveTrain.instance;
  }
}