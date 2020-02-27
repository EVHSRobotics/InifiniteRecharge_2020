/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class TurnAngle extends CommandBase {
  static double errorInt = 0;
  double target = 90;
  double kP;
  double kI, kD;
  double error, maxError;
  double pTerm;
  double iTerm, dTerm;
  int targetCount;
  double lastError;
  /**
   * Creates a new GyroTurn.
   */
  private DriveTrain jDrive;

  public TurnAngle(double angle) {
    this.target = angle;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Initializing turn angle");
    jDrive = RobotContainer.drive;
    jDrive.resetAngle();
    targetCount = 0;
    error = target - jDrive.returnAngle();

    lastError = error;
    errorInt = 0;
    maxError = 1000;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    kP = .008;
    kI = 0;// 0.0001;
    kD = 0.015;
    error = target - jDrive.returnAngle();
    pTerm = error * kP;
    iTerm = errorInt * kI;
    dTerm = (error - lastError)*kD;
 //   SmartDashboard.putNumber("Angle turn ERROr", error);
    jDrive.setSpeed(0, pTerm + iTerm + dTerm);
    errorInt += error;

    if(errorInt > maxError){
      errorInt = maxError;
    }else if(errorInt < -maxError){
      errorInt = -maxError;
    }

    if(Math.abs(error) > .1 && error * errorInt < 0){
      error = 0;
    }
    SmartDashboard.putNumber("Current angle", jDrive.returnAngle());
    System.out.println("Current angle " + jDrive.returnAngle());
    lastError = error;

   
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(target - jDrive.returnAngle()) <= .5 ){
      targetCount++;
    }else{
      targetCount = 0;
    }

    if(targetCount>5){
      System.out.println("Turn angle finished");
      return true;
    }
    return false;
     
  }
}