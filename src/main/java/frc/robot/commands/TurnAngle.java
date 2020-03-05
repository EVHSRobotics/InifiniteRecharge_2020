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
  int targetCount, diffTargetCount;
  double lastError, errorDiff;
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
    diffTargetCount = 0;
    error = target - jDrive.returnAngle();

    lastError = error;
    errorInt = 0;
    maxError = 10000;
    kP = .008;
    kI = 0;// 0.0001;
    kD = 0.015;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    error = target - jDrive.returnAngle();
    errorInt += error;
    errorDiff = error - lastError;
    pTerm = error * kP;
    iTerm = errorInt * kI; 
    dTerm = errorDiff*kD;
    
    jDrive.setSpeed(0, pTerm + iTerm + dTerm);
   

    if(errorInt > maxError){
      errorInt = maxError;
    }else if(errorInt < -maxError){
      errorInt = -maxError;
    }

    if(Math.abs(error) > .1 && error * errorInt < 0){
      errorInt = 0;
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
    if ((Math.abs(target - jDrive.returnAngle()) <= 5)){
      targetCount++;
    }else{
      targetCount = 0;
    }

    if(Math.abs(errorDiff) <= .01){
      diffTargetCount++;
    }else{
      diffTargetCount = 0;
    }
    if(targetCount>5 || diffTargetCount > 10){
      System.out.println("Turn angle finished");
      return true;
    }
    return false;

    
  }
}