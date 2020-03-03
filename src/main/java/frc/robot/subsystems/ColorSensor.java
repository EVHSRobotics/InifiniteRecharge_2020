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

  private final static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.441, 0.415);
  private final static Color kGreenTarget = ColorMatch.makeColor(0.175, 0.576, 0.248);
  private final static Color kRedTarget = ColorMatch.makeColor(0.511, 0.352, 0.135);
  private final static Color kYellowTarget = ColorMatch.makeColor(0.314, 0.56, 0.123);
  private final static Color kBlackTarget = ColorMatch.makeColor(0, 0, 0);
  private final static Color kWoodTarget = ColorMatch.makeColor(0.312, 0.493, 0.194);

  static int timesblue = 0;
  static int timesgreen = 0;
  static int timesred = 0;
  static int timesyellow = 0;
  static Boolean spinwheel = false;
  static int lastcolor = 5;
  
  static String colorString = "";
  static String colorFound = "";
  
  private static TalonSRX wheelspinner = new TalonSRX(Constants.COLOR_WHEEL_SPINNER); //change this once the colorwheel spinner has been installed

  public ColorSensor() {
    //initializing code
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    m_colorMatcher.addColorMatch(kBlackTarget);
    m_colorMatcher.addColorMatch(kWoodTarget);

  }

  @Override
  public void periodic() {
  }

  public static void run() {

    Color detectedColor = m_colorSensor.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    SmartDashboard.putBoolean("Status of spinner:", spinwheel);
    SmartDashboard.putNumber("Times Blue:", timesblue);
    SmartDashboard.putNumber("Times Green:", timesgreen);
    SmartDashboard.putNumber("Times Red:", timesred);
    SmartDashboard.putNumber("Times Yellow:", timesyellow);



    if(timesblue >= 6 && timesgreen >= 6 && timesred >= 6 && timesyellow >= 6){
    spinwheel = false;
    }else {
    spinwheel = true;
    }

    if(spinwheel == true){
    wheelspinner.set(ControlMode.PercentOutput, 0.75);
    }else{
    wheelspinner.set(ControlMode.PercentOutput, 0); 
    }
    
    
    if (match.color == kBlueTarget && lastcolor != 1) {
    colorString = "Blue";
    timesblue++;
    lastcolor = 1;
    } else if (match.color == kRedTarget && lastcolor != 2) {
    colorString = "Red";
    timesred++;
    lastcolor = 2;
    } else if (match.color == kGreenTarget && lastcolor != 3) {
    colorString = "Green";
    timesgreen++;
    lastcolor = 3;
    } else if (match.color == kYellowTarget && lastcolor != 4) {
    colorString = "Yellow";
    timesyellow++;
    lastcolor = 4;
    } else if (match.color == kBlackTarget) {
    colorString = "Black";
    } else if (match.color == kWoodTarget) {
    colorString = "Wood";
    } else {
      if(match.color == kBlackTarget || match.color == kBlueTarget || match.color == kGreenTarget || match.color == kRedTarget || match.color == kWoodTarget || match.color == kYellowTarget){
      }else{
      colorString = "Unknown";
      }
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

  public static void endme(){
    wheelspinner.set(ControlMode.PercentOutput, 0);

    timesblue = 0;
    timesgreen = 0;
    timesred = 0;
    timesyellow = 0;
  }

  public static void turntoColor(String col) {
    
    Color a = m_colorSensor.getColor();
    ColorMatchResult l = m_colorMatcher.matchClosestColor(a);

    if(l.color == kRedTarget){
      colorFound = "red";
    }else if(l.color == kBlueTarget){
      colorFound = "blue";
    }else if(l.color == kYellowTarget){
      colorFound = "yellow";
    }else if(l.color == kRedTarget){
      colorFound = "red";
    }else{
      colorFound = "";
    }
    SmartDashboard.putString("Detected: ", colorFound);
    SmartDashboard.putString("COLR: ", col);
    if(col.equals(colorFound)){
      wheelspinner.set(ControlMode.PercentOutput, 0);
      SmartDashboard.putString("OK: ", "REACHED");
    }else{
      wheelspinner.set(ControlMode.PercentOutput, .75);
      SmartDashboard.putString("OK: ", "NOT REACHED");
    }

  }
  
}
