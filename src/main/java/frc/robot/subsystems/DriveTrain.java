/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Drive;
/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  private static TalonSRX l1;
  private static TalonSRX l2;
  private static TalonSRX r1;
  private static TalonSRX r2;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    l1 = new TalonSRX(RobotMap.L1_ID);
    l2 = new TalonSRX(RobotMap.L2_ID);
    r1 = new TalonSRX(RobotMap.R1_ID);
    r2 = new TalonSRX(RobotMap.R2_ID);
    l1.setInverted(true);
    l2.setInverted(true);
    // Set the default command for a subsystem here.
  }

  public static void move(double p, double turn) {
    l1.set(ControlMode.PercentOutput, p + turn);
    l2.set(ControlMode.PercentOutput, p + turn);
    r1.set(ControlMode.PercentOutput, p - turn);
    r2.set(ControlMode.PercentOutput, p - turn);
  }
  
}
