/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class GyroTurn extends CommandBase {
  static int lastError = 0;
  /**
   * Creates a new GyroTurn.
   */
  private final DriveTrain jDrive;
  public GyroTurn(DriveTrain d) {
    addRequirements(d);
    jDrive = d;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    jDrive.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      double kP = 0.038;
      double kI = 0.000001;
      double target = 90;
      double error = target - jDrive.returnAngle();
      double pTerm = error*kP;
      double iTerm = lastError*kI;
      SmartDashboard.putNumber("ERROr", error);
      jDrive.setSpeed(0, -pTerm + iTerm);
      // System.out.println("ANGLE: " + jDrive.returnAngle());
      // while(true){
      //   SmartDashboard.putNumber("ANGLE", jDrive.returnAngle());
      // }
      lastError += error;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
