package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


public final class Constants {
  public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(0.5588);

  public static final double ksVolts = 11;
  public static final double kvVoltSecondsPerMeter = 0.1667;
  public static final double kaVoltSecondsSquaredPerMeter = 0.0417;

  public static final double kMaxSpeedMetersPerSecond = 0.75;
  public static final double kMaxAccelerationMetersPerSecondSquared = 0.75;

  public static final double MAX_VOLTAGE = 11.8;
}