/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  private TalonSRX leftTalon1 = new TalonSRX(Constants.LEFT_MOTOR_1);
  private TalonSRX leftTalon2= new TalonSRX(Constants.LEFT_MOTOR_2);
  private TalonSRX rightTalon1 = new TalonSRX(Constants.RIGHT_MOTOR_1);
  private TalonSRX rightTalon2 = new TalonSRX(Constants.RIGHT_MOTOR_2);
 
  public DriveTrain() {
      leftTalon2.follow(leftTalon1);
      rightTalon2.follow(rightTalon1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpeed(double speed){
    leftTalon1.set(ControlMode.PercentOutput, speed);
    rightTalon1.set(ControlMode.PercentOutput, speed);
  }

  
}
