/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class JoystickDrive extends CommandBase {
  private double throttle;
  private double turn;
  private final DriveTrain jDrive;
  /**
   * Creates a new JoystickDrive.
   */
  public JoystickDrive(DriveTrain drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    jDrive = drive;
   
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("joystick command working");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    throttle = RobotContainer.joy.getRawAxis(5);
    turn = RobotContainer.joy.getRawAxis(2) - RobotContainer.joy.getRawAxis(3);//   System.out.println("throttle: " + throttle);
    
    
 // jDrive.setSpeedPercent(throttle+turn, throttle - turn);
    
    jDrive.setSpeed(throttle + turn);

   // System.out.println("Left Encoder = " + jDrive.getLeftEncoders());



  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    jDrive.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
