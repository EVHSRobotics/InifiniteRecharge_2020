/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied
 by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotContainer;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorMatch;

public class ColorSensor extends SubsystemBase {
  /**
   * Creates a new ColorSensor.
   */
  private final static I2C.Port i2cPort = I2C.Port.kOnboard;
  private final static ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final static ColorMatch m_colorMatcher = new ColorMatch();

  private final static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final static Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final static Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  static int timesblue = 0;
  static int timesgreen = 0;
  static int timesred = 0;
  static int timesyellow = 0;
  static Boolean spinwheel = false;
  static int lastcolor = 0; 
  
  //private static TalonSRX wheelspinner = new TalonSRX(Constants.COLOR_WHEEL_SPINNER); //change this once the colorwheel spinner has been installed

  public ColorSensor() {
    //initializing code
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    
  }

  @Override
  public void periodic() {
  }

  public static void run() {

    Color detectedColor = m_colorSensor.getColor();
    String colorString = "";
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    SmartDashboard.putBoolean("Status of spinner:", spinwheel);
    SmartDashboard.putNumber("Times Blue:", timesblue);
    SmartDashboard.putNumber("Times Green:", timesgreen);
    SmartDashboard.putNumber("Times Red:", timesred);
    SmartDashboard.putNumber("Times Yellow:", timesyellow);


    if(timesblue == 4){
      if(timesgreen == 4){
        if(timesred == 4){
          if(timesyellow == 4){
            spinwheel = false;
          }
        }
      }
    }

    if (match.color == kBlueTarget && lastcolor != 1) {
    timesblue++;
    lastcolor = 1;
    } else if (match.color == kRedTarget && lastcolor != 2) {
    timesred++;
    lastcolor = 2;
    } else if (match.color == kGreenTarget && lastcolor != 3) {
    timesgreen++;
    lastcolor = 3;
    } else if (match.color == kYellowTarget && lastcolor != 4) {
    timesyellow++;
    lastcolor = 4;
    } else {
    colorString = "Unknown";
    }

    while(spinwheel == true){
      //wheelspinner.set(ControlMode.PercentOutput, 1);
    }

    /**
   * Open Smart Dashboard or Shuffleboard to see the color detected by the 
   * sensor.
    */
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    // return 0;
  }
}
