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
  // private static TalonFX intakeMotor1 = new TalonFX(Constants.INTAKE_1);
  // private static TalonFX intakeMotor2 = new TalonFX(Constants.INTAKE_2);
  // private static TalonFX turretMotor = new TalonFX(Constants.TURRET);
  private static TalonFX shooterMotor1 = new TalonFX(Constants.SHOOTER1);
  private static TalonFX shooterMotor2 = new TalonFX(Constants.SHOOTER2);

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
    shooterMotor1.set(ControlMode.PercentOutput, speed);
    shooterMotor2.set(ControlMode.PercentOutput, speed);
    System.out.println(speed);
  }

  // public void rotateTurret(double speed){
  //   turretMotor.set(ControlMode.PercentOutput,speed);
  // }

  // public void intake1(double speed){
  //   intakeMotor1.set(ControlMode.PercentOutput, speed);
  // }

  // public void intake2(double speed){
  //   intakeMotor2.set(ControlMode.PercentOutput, speed);
  // }
}
