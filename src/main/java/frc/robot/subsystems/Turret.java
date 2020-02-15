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
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
  private TalonSRX turretMotor = new TalonSRX(Constants.TURRET);
 // private TalonSRX elevator = new TalonSRX(Constants.ELEVATOR);
  Vision vision;
  double kp;
  double kf;
  double deadZone;
  double fric;
  private DigitalInput limitSwitch_left;
  private DigitalInput limitSwitch_right;
  /**
   * Creates a new Turret.
   */
  public Turret(Vision vision) {
   this.vision = vision;
   limitSwitch_left = new DigitalInput(1);
   limitSwitch_right = new DigitalInput(0);
   
    kp = .03;
    kf = 0.1;
    deadZone = 1;
    fric = 0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   // System.out.println("is limit 0 there? -> " + limitSwitch_left.get());

    //System.out.println("Limit switch reading for port 0: " + limitSwitch_left.readRisingTimestamp());
  }

  public void turnTurret(double trigger){
    //double desiredSpeed = kp*vision.getX();
    double posErr = vision.getX();
    fric = (kf / deadZone) * posErr;
    if(fric > kf){
      fric = kf;
    }
    if(fric < -kf){
      fric = -kf;
    }

  //  System.out.println("Turret Speed: " + trigger*(posErr * kp + fric));

    turretMotor.set(ControlMode.PercentOutput, trigger*(posErr * kp + fric));
  }

  public boolean getLeftLimitSwitchStatus(){//right
    return limitSwitch_left.get();
  }
  public boolean getRightLimitSwitchStatus(){ //left
    return limitSwitch_right.get();
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
