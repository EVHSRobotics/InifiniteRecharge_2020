/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  private TalonFX leftFalcon1;
  private TalonFX leftFalcon2;
  private TalonFX rightFalcon1;
  private TalonFX rightFalcon2;
 
  public DriveTrain() {
    leftFalcon1 = new TalonFX(Constants.LEFT_MOTOR_1);
    leftFalcon2 = new TalonFX(Constants.LEFT_MOTOR_2);
    rightFalcon1 = new TalonFX(Constants.RIGHT_MOTOR_1);
    rightFalcon2 = new TalonFX(Constants.RIGHT_MOTOR_2);

    leftFalcon2.follow(leftFalcon1);
    rightFalcon2.follow(rightFalcon1);

    rightFalcon1.setInverted(true);
    rightFalcon2.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpeed(double speed){
    leftFalcon1.set(ControlMode.PercentOutput, speed);
    rightFalcon1.set(ControlMode.PercentOutput, speed);
  }

  
}
