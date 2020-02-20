/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMTalonFX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.TalonFXSpeedController;
import frc.robot.commands.ToggleShift;

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

  private DoubleSolenoid shifter;
  private DoubleSolenoid shifter2;
  Value fast = Value.kForward;
  Value slow = Value.kReverse;
  Value off = Value.kOff;
  private String gearState;
  private boolean isFast = false;
  private JoystickButton buttonA;
  DifferentialDrive dDrive;

  private int leftEncoderTicks;
  private int rightEncoderTicks;
  private int averageEncoderTicks = (leftEncoderTicks + rightEncoderTicks) / 2;
  private double distanceTraveled;
  private double wheelDiameter = 6;
  private double circumference = Math.PI * wheelDiameter;
  private double ticksPerInch = 360 / circumference;

  public DriveTrain() {
    leftFalcon1 = new TalonFXSpeedController(Constants.LEFT_MOTOR_1);
    leftFalcon2 = new TalonFXSpeedController(Constants.LEFT_MOTOR_2);
    rightFalcon1 = new TalonFXSpeedController(Constants.RIGHT_MOTOR_1);
    rightFalcon2 = new TalonFXSpeedController(Constants.RIGHT_MOTOR_2);

    leftGroup = new SpeedControllerGroup(leftFalcon1, leftFalcon2);
    rightGroup = new SpeedControllerGroup(rightFalcon1, rightFalcon2);
    // leftFalcon2.follow(leftFalcon1);
    // rightFalcon2.follow(rightFalcon1);

    // rightFalcon1.setInverted(true);
    // rightFalcon2.setInverted(true);

    dDrive = new DifferentialDrive(leftGroup, rightGroup);
    shifter = new DoubleSolenoid(Constants.shifterUp1, Constants.shifterDown1);
    shifter2 = new DoubleSolenoid(Constants.shifterUp2, Constants.shifterDown2);
    applyShift("low");

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  public void drive(double speed, double turn) {
    //[]\System.out.println("setting speed");
    dDrive.curvatureDrive(speed, turn, (Math.abs(speed) < 0.1));
    SmartDashboard.putNumber("Avg Encoder", getAverageEncoders());
  }

  public void driveDistance(double distance) {
    // System.out.println("drive distance working");
    // double tickGoal =75 ticksPerInch * distance;
    // leftEncoderTicks = leftFalcon1.getEncoderTicks();
    // rightEncoderTicks = rightFalcon1.getEncoderTicks();

    while (averageEncoderTicks > -32400) {
      drive(-.1, 0);
    }
    drive(0, 0);
    System.out.println("finished driving distance");
  }

 

  public void resetEncoders() {
    leftFalcon1.resetEncoder();
    rightFalcon1.resetEncoder();
  }

  public void toggleShift() {
    if (isFast)
      applyShift("slow");
    else if (!isFast)
      applyShift("fast");
    isFast = !isFast;
  }

  public void applyShift(String gear) {
    if (gear.equals("fast")) {
      gearState = "fast";
      shifter.set(fast);
      shifter2.set(fast);
      System.out.println("shifted to fast");
    } else if (gear.equals("slow")) {
      gearState = "slow";
      shifter.set(slow);
      shifter2.set(slow);
      System.out.println("shifted to slow");
    }
  }

  public void stopShift() {
    shifter.set(off);
    shifter2.set(off);
  }

  public DoubleSolenoid getShifter1() {
    return shifter;

  }

  public DoubleSolenoid getShifter2() {
    return shifter2;
  }


  public double getTicksPerInch() {
    return ticksPerInch;
  }

  public int getAverageEncoders(){
    return (leftFalcon1.getEncoderTicks() - rightFalcon1.getEncoderTicks())/2;

  }
}
