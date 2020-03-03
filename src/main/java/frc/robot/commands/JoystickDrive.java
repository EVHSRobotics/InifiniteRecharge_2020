/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;

public class JoystickDrive extends CommandBase {
  private double throttle;
  private double turn;
  private final DriveTrain jDrive;
  Vision vision;
  /**
   * Creates a new JoystickDrive.
   */
  public JoystickDrive(DriveTrain drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    jDrive = drive;
    //vision = RobotContainer.vision;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  //   throttle = RobotContainer.joy.getRawAxis(2)-RobotContainer.joy.getRawAxis(3);
  //   turn = RobotContainer.joy.getRawAxis(4);
  //   turn *= Math.abs(turn)*turn*turn;
  //   if(Math.abs(turn) < .005){
  //     turn = 0;

  //   }
    
  //   System.out.println(throttle);
  //     //jDrive.setSpeed(throttle, -1*turn);

  //   System.out.println("Battery Voltage: " + RobotController.getBatteryVoltage());
  //  // SmartDashboard.putNumber("Battery Voltage", RobotController.getBatteryVoltage());
    System.out.println("x = " + vision.getX());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   // jDrive.setSpeed(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
