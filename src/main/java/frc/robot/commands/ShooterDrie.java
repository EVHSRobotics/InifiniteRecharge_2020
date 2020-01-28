/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;

public class ShooterDrie extends CommandBase {
  private Shooter jShooter;
  private double throttle;
  /**
   * Creates a new ShooterDrie.
   */
  public ShooterDrie(Shooter shoot) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shoot);
    jShooter = shoot;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    throttle = RobotContainer.joy.getRawAxis(3) - RobotContainer.joy.getRawAxis(2);
    jShooter.outtakeBall(throttle);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    jShooter.outtakeBall(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
