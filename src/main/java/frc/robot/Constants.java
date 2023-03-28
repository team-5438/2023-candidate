// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import frc.robot.utils.ControllerInit;
import frc.robot.utils.MotorController;

public final class Constants {
    public static abstract class Drivetrain {

        public static final MotorController[] LEFT_SPARKS = {
            new MotorController(1, true, 'L'), // back left
            new MotorController(2, true, 'L'), // front left
        };
        public static final MotorController[] RIGHT_SPARKS = {
            new MotorController(3, false, 'R'), // back right
            new MotorController(4, false, 'R'), // front right
        };

        public static final ControllerInit[] CONTROLLERS = {
            new ControllerInit(3, 'X'),
            new ControllerInit(4, 'P'),
        };
    }
}