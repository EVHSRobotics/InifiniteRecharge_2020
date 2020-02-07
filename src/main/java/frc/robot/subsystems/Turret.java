/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
  private TalonSRX turret = new TalonSRX(Constants.TURRET);
  private TalonSRX elevator = new TalonSRX(Constants.ELEVATOR);
  Vision vision;
  double kp;
  /**
   * Creates a new Turret.
   */
  public Turret() {
    vision = RobotContainer.vision;
    kp = .01;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void turnTurret(double speed){
    
    turret.set(ControlMode.PercentOutput, speed);//*kp*vision.getX());
  }

  // public void elevateBall(double rpm){
  //   while(RobotContainer.shooter.getShooterSpeed() < RobotContainer.shooter.getMaxSpeed()){
  //     System.out.println(RobotContainer.shooter.getShooterSpeed());
  //     setElevatorSpeed(0);
  //   }
  //   elevator.set(ControlMode.Velocity, rpm);
  // }

  // public void setElevatorSpeed(double speed){
  //   elevator.set(ControlMode.Velocity, speed);
  // }
}
