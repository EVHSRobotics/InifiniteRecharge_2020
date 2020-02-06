/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class ToggleShift extends CommandBase {
  private DriveTrain drive;
  private int num;
  /**
   * Creates a new ToggleShift.
   */
  public ToggleShift(DriveTrain drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    this.drive = drive;
   // drive.toggleShift();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("shifting gear");
   

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.toggleShift();
    drive.setSpeed(0, 0);
   
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   // drive.stopShift();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
