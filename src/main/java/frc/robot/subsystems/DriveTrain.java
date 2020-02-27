/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PWMTalonFX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.TalonSRXSpeedController;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */

  private TalonSRXSpeedController leftTalon1;
  private TalonSRXSpeedController leftTalon2;
  private TalonSRXSpeedController leftTalon3;
  private TalonSRXSpeedController rightTalon1;
  private TalonSRXSpeedController rightTalon2;
  private TalonSRXSpeedController rightTalon3;

  private SpeedControllerGroup leftGroup;

  private SpeedControllerGroup rightGroup;
  
  public static AHRS navX;

  DifferentialDrive dDrive;

  public DriveTrain() {
    // leftFalcon1 = new TalonFXSpeedController(Constants.LEFT_MOTOR_1, Constants.LEFT_MOTOR_2);
    // // leftFalcon2 = new TalonFXSpeedController(Constants.LEFT_MOTOR_2);
    // rightFalcon1 = new TalonFXSpeedController(Constants.RIGHT_MOTOR_1, Constants.RIGHT_MOTOR_2);
    // // rightFalcon2 = new TalonFXSpeedController(Constants.RIGHT_MOTOR_2);

    // leftGroup = new SpeedControllerGroup(leftFalcon1, leftFalcon2);
    // rightGroup = new SpeedControllerGroup(rightFalcon1, rightFalcon2);

    // // rightFalcon1.setInverted(false);
    // // rightFalcon2.setInverted(false);
    // dDrive = new DifferentialDrive(leftFalcon1, rightFalcon1);
    System.out.println("initialized drive train");
    leftTalon1 = new TalonSRXSpeedController(Constants.LEFT_MOTOR_1, Constants.LEFT_MOTOR_2);
  //  leftTalon2 = new TalonSRXSpeedController(Constants.LEFT_MOTOR_2);
    
    rightTalon1 = new TalonSRXSpeedController(Constants.RIGHT_MOTOR_1, Constants.RIGHT_MOTOR_2);
    //rightTalon2 = new TalonSRXSpeedController(Constants.RIGHT_MOTOR_2);


    
    leftGroup = new SpeedControllerGroup(leftTalon1);
    rightGroup = new SpeedControllerGroup(rightTalon1);

    dDrive = new DifferentialDrive(leftGroup, rightGroup);
    navX = new AHRS(Port.kUSB1);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpeed(double speed, double turn) {
    // dDrive.set(ControlMode.PercentOutput, speed);

    dDrive.curvatureDrive(speed, turn, (Math.abs(speed) < 0.1));
    // System.out.println("left talon encoder: " + leftTalon1.getEncoderTicks());
    // System.out.println("right talon encoder: " + rightTalon1.getEncoderTicks());

  }

  public void resetEncoders() {
    leftTalon1.resetEncoder();
    rightTalon1.resetEncoder();
  }

  public int getAvgEncoders() {
    System.out.println("Falcon left 1 get(): " + leftTalon1.getEncoderTicks() + " RIGHT: " + rightTalon1.getEncoderTicks());
    return (leftTalon1.getEncoderTicks()-rightTalon1.getEncoderTicks())/2;

   
  }

  public double returnAngle() {
    return navX.getAngle();
  }

  public void resetAngle() {
    navX.reset();
  }
}
