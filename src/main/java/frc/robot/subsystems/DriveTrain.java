/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.PWMTalonFX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.TalonFXSpeedController;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
 
  private TalonFXSpeedController leftFalcon1;
  private TalonFXSpeedController leftFalcon2;
  private TalonFXSpeedController rightFalcon1;
  private TalonFXSpeedController rightFalcon2;

  private SpeedControllerGroup leftGroup;
  
  private SpeedControllerGroup rightGroup;


  DifferentialDrive dDrive;

  

  public DriveTrain() {
    leftFalcon1 = new TalonFXSpeedController(Constants.LEFT_MOTOR_1);
    leftFalcon2 = new TalonFXSpeedController(Constants.LEFT_MOTOR_2);
    rightFalcon1 = new TalonFXSpeedController(Constants.RIGHT_MOTOR_1);
    rightFalcon2 = new TalonFXSpeedController(Constants.RIGHT_MOTOR_2);

    leftGroup = new SpeedControllerGroup(leftFalcon1, leftFalcon2);
    rightGroup = new SpeedControllerGroup(rightFalcon1, rightFalcon2);
  //  leftFalcon2.follow(leftFalcon1);
   // rightFalcon2.follow(rightFalcon1);

   // rightFalcon1.setInverted(true);
  //  rightFalcon2.setInverted(true);

    dDrive = new DifferentialDrive(leftGroup , rightGroup);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpeed(double speed, double turn){
    //dDrive.set(ControlMode.PercentOutput, speed);
    
    //leftFalcon1.set(ControlMode.PercentOutput, speed);

   // rightFalcon1.set(ControlMode.PercentOutput, speed);
    //dDrive.arcadeDrive(speed, turn, true);
    dDrive.curvatureDrive(speed, turn, (Math.abs(speed)<0.1));
  //  leftGroup.set(speed);
  //  rightGroup.set(speed);
  //  leftFalcon1.set(speed);
  //  leftFalcon2.set(speed);
  //  rightFalcon1.set(speed);
  //  rightFalcon2.set(speed);
   System.out.println("Falcon left 1 get(): " + leftFalcon1.get());
   System.out.println("Falcon right 1 get(): " + rightFalcon1.get());
   System.out.println("turn: " + turn);
  }

  
}
