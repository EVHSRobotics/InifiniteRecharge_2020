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

public class Shooter extends SubsystemBase {
  private static TalonFX shooterMotor1 = new TalonFX(Constants.SHOOTER_MOTOR_1);
  private static TalonFX shooterMotor2 = new TalonFX(Constants.SHOOTER_MOTOR_2);
  private double currentSpeed;
  private double maxSpeed;

  /**
   * Creates a new Shooter.
   */
  public Shooter() { 
      shooterMotor2.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void outtakeBall(double speed){
    System.out.println("Outtake command is working");
    maxSpeed = speed;
    shooterMotor1.set(ControlMode.Velocity, speed*20000);
   // shooterMotor2.set(ControlMode.Velocity, speed*2000000);
    System.out.println("speed " + speed);
  }

  public double getShooterSpeed(){
    return shooterMotor1.getSelectedSensorVelocity();
  
  }
  public double getMaxSpeed(){
    return maxSpeed;
  }


}
