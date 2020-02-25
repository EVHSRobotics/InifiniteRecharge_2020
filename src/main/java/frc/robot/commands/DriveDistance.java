/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class DriveDistance extends CommandBase {
  DriveTrain drive;
  double initencoder;
  int count;
  int target = 100;
  static int lastError = 0;

  
  /**
   * Creates a new DriveDistance.
   */
  public DriveDistance(DriveTrain jdrive) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive = jdrive;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
   // drive.resetEncoders();
    initencoder = drive.getAvgEncoders();
    count = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double kP = 0.0;
    double kF = 0.0;
    double kI = 0.0;
    double error = target - drive.getAvgEncoders();
    double pTerm = error * kP;
    double iTerm = lastError * kI;

    drive.setSpeed(.5, 0); 
  
    count++;
    SmartDashboard.putNumber("Initial value of encoders:", initencoder);
    SmartDashboard.putNumber("encoders", drive.getAvgEncoders());

  //  System.out.println("AVerage encoders:" + drive.getAvgEncoders());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Reached Distance");
    drive.setSpeed(0,0);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
  
    return (Math.abs(drive.getAvgEncoders()) - initencoder > target);

  }
}