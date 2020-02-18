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
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class JoystickDrive extends CommandBase {
  private double throttle;
  private double turn;
  private final DriveTrain jDrive;
  private JoystickButton buttonA;

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
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //throttle = Robot.robotContainer.getJoy().getRawAxis(1);// - Robot.robotContainer.getJoy().getRawAxis(2);
    throttle = SmartDashboard.getNumber("set speed", 0);
    turn = Robot.robotContainer.getWheel().getRawAxis(0);
    //turn *= Math.abs(turn) * turn ;
    //if(jDrive.getShifter1().get().)
    if (Math.abs(turn) < .0001) {
      turn = 0;

    }

    System.out.println(throttle);
    jDrive.setSpeed(throttle, -1 * turn);

    if(Robot.robotContainer.getJoy().getRawButton(3)) {
      for (int i = 0; i < 4; i++) {
        jDrive.turn180();
      }
      System.out.println("bruh");
    }

      
    System.out.println("Battery Voltage: " + RobotController.getBatteryVoltage());
   
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    jDrive.setSpeed(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
