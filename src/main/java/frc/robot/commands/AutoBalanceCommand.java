package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.*;

public class AutoBalanceCommand extends CommandBase
{
  private DrivetrainSubsystem drive;
  private double speed = 0.3;
  private int OffBalance = 7;

  public AutoBalanceCommand(DrivetrainSubsystem m_drive)
  {
    m_drive = drive;
  }

  @Override
  public void initialize()
  {
    drive.arcadeDrive(0, 0);
  }

  @Override
  public void execute()
  {
    speed = drive.getPitch() / 150;

    if (drive.getPitch() >= OffBalance)
    {
      drive.arcadeDrive(speed, 0);
    }
    else if (drive.getPitch <= OffBalance)
    {
      drive.arcadeDrive(-speed, 0)
    }
    else
    {
      drive.arcadeDrive(0, 0);
    }
  }

  public void end()
  {
    drive.arcadeDrive(0, 0);
  }
}