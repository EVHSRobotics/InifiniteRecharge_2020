/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// import com.revrobotics.ColorSensorV3;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  // private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private static TalonSRX leftTalon1 = new TalonSRX(Constants.LEFT_MOTOR_1);
  private static TalonSRX leftTalon2 = new TalonSRX(Constants.LEFT_MOTOR_2); //invert
  private static TalonSRX rightTalon1= new TalonSRX(Constants.RIGHT_MOTOR_1);
  private static TalonSRX rightTalon2 = new TalonSRX(Constants.RIGHT_MOTOR_2);//invert
 
 
  public DriveTrain() {
      leftTalon2.follow(leftTalon1);
      rightTalon2.follow(rightTalon1);
      System.out.println("Drivetrain working");
      leftTalon1.setInverted(true);
      leftTalon2.setInverted(true);
     
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpeed(double speed, double turn){
    leftTalon1.set(ControlMode.Velocity, (speed+turn)*1000);
    rightTalon1.set(ControlMode.Velocity, (speed-turn)*1000);
  }

  public void setSpeedPercent(double rightSpeed, double leftSpeed){
   
      leftTalon1.set(ControlMode.PercentOutput, leftSpeed);
      rightTalon1.set(ControlMode.PercentOutput, rightSpeed);
     // rightTalon2.set(ControlMode.PercentOutput, speed);
  }

  // public Color returnCol(){
  //   Color detectedColor = m_colorSensor.getColor();
  //   return detectedColor;
  // }

  // public double returnIR(){
  //   double IR = m_colorSensor.getIR();
  //   return IR;
  // }

  // public double proximity(){
  //   double prox = m_colorSensor.getProximity();
  //   return prox;
  // }

  

  // public double getLeftEncoders(){
  //   return talon.getSelectedSensorPosition(0);
  // }

  // public void resetEncoders(){
  //    talon.setSelectedSensorPosition(0);
  //}

  

  
}
