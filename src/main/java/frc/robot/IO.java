package frc.robot;

/* import org.frc.robot.commands.claw.ClawOutputCommand;
import org.frc.robot.commands.claw.ClawToggleCommand;
import org.frc.robot.commands.climber.ForkCommand;
import org.frc.robot.commands.VisionToggleCommand;
import org.frc.robot.commands.climber.ClimbBrakeCommand; */
/* import org.frc.robot.commands.DriveTrain.DriveOutputCommand;
import org.frc.robot.commands.DriveTrain.VisionAlignCommand; */
/* import org.frc.robot.commands.elevator.ElevatorBrakeToggleCommand;
import org.frc.robot.commands.elevator.ElevatorPositionCommand;
import org.frc.robot.commands.intake.IntakeSlideCommand;
import org.frc.robot.commands.preset.PresetCommand;
import org.frc.robot.commands.wrist.WristControllerCommand;
import org.frc.robot.utilities.CGUtils; */
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.utils.ControllerInit;

public class IO {

    private static IO instance;

    XboxController driver = ControllerInit.driver;
    PS4Controller operator = ControllerInit.operator;

    private IO() {

        // this.operator.leftClick.whenPressed(new ClawToggleCommand());
        // this.operator.rightClick.whenPressed(new IntakeSlideCommand());
        //
        // this.operator.dPad.up.whileHeld(new WristControllerCommand(0.35));
        // this.operator.dPad.down.whileHeld(new WristControllerCommand(-0.35));
        // this.operator.dPad.left.whenPressed(new ElevatorBrakeToggleCommand());
        // this.operator.dPad.right.whenPressed(new ForkCommand());
        //
        // this.operator.y.whileHeld(new PresetCommand(PresetCommand.Preset.HIGH));
        // this.operator.b.whileHeld(new PresetCommand(PresetCommand.Preset.MED));
        // this.operator.a.whileHeld(new PresetCommand(PresetCommand.Preset.LOW));
        // this.operator.x.whileHeld(new PresetCommand(PresetCommand.Preset.BASE));
        //
        // this.operator.rightBumper.whileHeld(new PresetCommand(PresetCommand.Preset.SHIP));
        // this.operator.leftBumper.whileHeld(new PresetCommand(PresetCommand.Preset.SHIP_FRONT));
        //
        // this.operator.back.whileHeld(new ClawOutputCommand(0.40));
        //
        // this.driver.leftBumper.whenPressed(VisionToggleCommand.getInstance());
        // this.driver.rightBumper.whileHeldOnce(new VisionAlignCommand(Robot.backLimelight));
        // this.driver.circle.whileHeldOnce(new VisionAlignCommand(Robot.frontLimelight));
        // this.driver.square.whileHeldOnce(new VisionAlignCommand(Robot.backLimelight));
        //
        // this.driver.pad.whileHeld(new DriveOutputCommand(0.60));
        //
        // this.driver.triangle.whileHeldOnce(new PresetCommand(PresetCommand.Preset.CLIMB));
        // this.driver.cross.whileHeldOnce(CGUtils.parallel(
        //         new ElevatorPositionCommand(-6144, 5120, 5120, false),
        //         new ClimbBrakeCommand()
        // ));
    }

    public XboxController getOperator() {
        return this.driver;
    }

    public PS4Controller getDriver() {
        return this.operator;
    }

    public static synchronized void initialize() {
        if (IO.instance == null) {
            IO.instance = new IO();
        }
    }

    public static IO getInstance() {
        return IO.instance;
    }
}