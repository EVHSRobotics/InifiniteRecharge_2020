/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;

public class GyroPID extends SubsystemBase {
  
  private AHRS navX;
  /**
   * Creates a new GyroPID.
   */


  public GyroPID() {
    navX = new AHRS(I2C.Port.kMXP);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public AHRS getNavX() {
		return navX;
	}
}
