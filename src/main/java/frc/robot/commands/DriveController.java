package frc.robot.commands;

import frc.robot.IO;
import frc.robot.subsystems.DriveTrain;

public class DriveController {
    protected void execute() {
        double power = Math.pow(IO.getInstance().getDriver().getLeftY(), 2);
        double turn = Math.pow(IO.getInstance().getDriver().getRightX(), 2);

        power *= Math.signum(IO.getInstance().getDriver().getLeftY());
        turn *= Math.signum(IO.getInstance().getDriver().getRightX());

        DriveTrain.getInstance().set(power + turn, power - turn);
    }

    protected boolean isFinished() {
        return false;
    }
}