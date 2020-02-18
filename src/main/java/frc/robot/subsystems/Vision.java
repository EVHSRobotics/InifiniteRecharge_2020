/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
  /**
   * Creates a new Vision.
   */
  
  NetworkTable table;
  NetworkTableEntry tx;
  NetworkTableEntry ty;
  NetworkTableEntry ta;
  NetworkTableEntry tv;
  // read values periodically
  double x;
  double y;
  double area;

  public Vision() {

    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
    tv = table.getEntry("tv");

    // read values periodically

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);

    System.out.println("x : " + x);
  }

  public double getX() {
    if(tv.getBoolean(false)){
      return 0;
    }
    return tx.getDouble(0.0);
  
  }
}
