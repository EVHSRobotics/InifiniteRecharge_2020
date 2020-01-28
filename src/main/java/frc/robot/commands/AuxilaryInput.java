/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

// import java.util.Timer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;

public class AuxilaryInput extends CommandBase {
  /**
   * Creates a new AuxilaryInput.
   */
  static Timer time = new Timer();
  private final Shooter shot;
  public AuxilaryInput(Shooter a) {
    addRequirements(a);
    shot = a;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shot.outtakeBall(1);
    // shot.outtakeBall(RobotContainer.xbox.getTriggerAxis(Hand.kLeft));
  
    // if(RobotContainer.xbox.getAButton()){
    //   shot.intake1(1);
    // }else{
    //   shot.intake1(0);
    // }

    // if(RobotContainer.xbox.getBButton()){
    //   shot.intake2(1);
    // }else{
    //   shot.intake2(0);
    // }

    // shot.rotateTurret(RobotContainer.xbox.getX(Hand.kLeft));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // shot.intake1(0);
    // shot.intake2(0);
    // shot.rotateTurret(0);
    // shot.outtakeBall(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
