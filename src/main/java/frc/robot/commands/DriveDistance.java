/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.TrapezoidalMotionProfile;
import frc.robot.subsystems.DriveTrain;

public class DriveDistance extends CommandBase {
  DriveTrain drive;
  double initencoder;
  int count, speedCount;
  int target;
  static int lastError = 0;
  double kP, kI, kD, kF, turnkP;
  double error, pTerm, iTerm, dTerm, fTerm, diffError;
  double currentV, velocity, vMax, commandedSpeed, aRef, vRef, aMax;
  double distTravelledAvg, pRef, pStop, pTarget, tStop;
  double currPos;
  double normalizedSpeed;
  boolean isForward;

  double initAngle;

  enum trapState {
    accState, constantState, brakeState, done
  };

  trapState currState;
  Timer timer;
  double prevTime, currTime, timeElapsed;

  TrapezoidalMotionProfile trap;

  /**
   * Creates a new DriveDistance.
   */
  public DriveDistance(int distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive = RobotContainer.drive;
    addRequirements(drive);
    target = distance;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Initialize drive distance");
    // drive.resetEncoders();
    initencoder = drive.getAvgEncoders();
    System.out.println("init encoder: " + initencoder);
    count = 0;
    currentV = 0;
    timer = new Timer();
    timer.start();
    
    

    pRef = 0;
    vRef = 0;
    vMax = 3000;
    aMax = 500;
    currState = trapState.accState;
    currPos = drive.getAvgEncoders() - initencoder;
  
    if(target < currPos){
      isForward = false;
      pTarget = -target;
    }
    if(target > currPos){
      isForward = true;
      pTarget = target;
    }

    initAngle = drive.returnAngle();
    turnkP = .015;
    trap = new TrapezoidalMotionProfile(3000, 500, pTarget);


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    trap.updateProfile();
    currentV = drive.getAvgSpeed();
    currPos = drive.getAvgEncoders() - initencoder;
    kP =  0.0005;
    kD = 0.0;
    kI = 0.0;
    kF = 1;

    if(isForward){
      error = trap.pRef - currPos;
      diffError = trap.vRef - currentV;
      fTerm = kF*trap.vRef;
    }else{
      error = -trap.vRef - currPos;
      diffError = -trap.vRef - currentV;
      fTerm = -kF * trap.vRef;
    }
    pTerm = error * kP;
    dTerm = diffError * kD;
    // iTerm = lastError * kI;
    commandedSpeed = pTerm + dTerm + fTerm;
    normalizedSpeed = commandedSpeed / 2200;


    if (normalizedSpeed > .2) {
      normalizedSpeed = .2;
    }
    if (normalizedSpeed < -.2) {
      normalizedSpeed = -.2;
    }
    drive.driveArcade(normalizedSpeed, turnkP * (initAngle - drive.returnAngle()));
    count++;
    System.out.println("current pos: " + currPos);

  }

  // public void trapProfile() {
  //   System.out.println("current state: " + currState);

  //   switch (currState) {
  //   case accState:
  //     aRef = aMax;
  //     break;
  //   case constantState:
  //     aRef = 0;
  //     break;
  //   case brakeState:
  //     aRef = -aMax;
  //     break;
  //   case done:
  //     aRef = 0;
  //     pRef = pTarget;
  //     vRef = 0;
  //     return;
      
  //   }

  //   currTime = timer.get();
  //   timeElapsed = currTime - prevTime;
  //   prevTime = currTime;

  //   vRef += aRef * timeElapsed;
  //   pRef += vRef * timeElapsed;

  //   pStop = pTarget - (.5 * vRef * vRef) / aMax;

  //   if (currState == trapState.accState && vRef > vMax) {
  //     currState = trapState.constantState;
  //     vRef = vMax;
  //   }
  //   if (currState != trapState.brakeState && pRef > pStop) {
  //     currState = trapState.brakeState;
  //   }

  //   if (vRef <= 0 || pRef > pTarget) {
  //     vRef = 0;
  //     pRef = pTarget;
  //     currState = trapState.done;
  //   }

  //   System.out.println("time elasped: " + timeElapsed);
  //   System.out.println("aRef: " + aRef);

  // }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Reached Distance");
    drive.setSpeed(0, 0);
    trap.end();
    // pRef = 0;
    // vRef = 0;
    // vMax = 100;
    // aMax = 100;
    // currState = trapState.accState;

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if (Math.abs(target - currPos) <= 10){
      count++;
    }else{
      count = 0;
    }
    if(Math.abs(currentV) <= .01){
      speedCount++;
    }else{
      speedCount = 0;
    }

    if (count  > 5 || speedCount > 50){
      return true;
    }else{
      return false;
    }

  }
}