/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Timer;

/**
 * Add your docs here.
 */

public class TrapezoidalMotionProfile {
   
   
   public double vRef, pRef;
    double vMax;
    double aRef, aMax;
    double pStop, pTarget;
    enum trapState {
        accState, constantState, brakeState, done
      };
    
      trapState currState;
      Timer timer;
      double prevTime, currTime, timeElapsed;

    public TrapezoidalMotionProfile(double vMax, double aMax, double pTarget){
        timer = new Timer();
        timer.start();
      
    
        pRef = 0;
        vRef = 0;
        this.vMax = vMax;
        this.aMax = aMax;
        this.pTarget = pTarget;
        currState = trapState.accState;
        
      
    }
    public void updateProfile() {
        System.out.println("current state: " + currState);
    
        switch (currState) {
        case accState:
          aRef = aMax;
          break;
        case constantState:
          aRef = 0;
          break;
        case brakeState:
          aRef = -aMax;
          break;
        case done:
          aRef = 0;
          pRef = pTarget;
          vRef = 0;
          return;
          
        }
    
        currTime = timer.get();
        timeElapsed = currTime - prevTime;
        prevTime = currTime;
    
        vRef += aRef * timeElapsed;
        pRef += vRef * timeElapsed;
    
        pStop = pTarget - (.5 * vRef * vRef) / aMax;
    
        if (currState == trapState.accState && vRef > vMax) {
          currState = trapState.constantState;
          vRef = vMax;
        }
        if (currState != trapState.brakeState && pRef > pStop) {
          currState = trapState.brakeState;
        }
    
        if (vRef <= 0 || pRef > pTarget) {
          vRef = 0;
          pRef = pTarget;
          currState = trapState.done;
        }
    
        System.out.println("time elasped: " + timeElapsed);
        System.out.println("aRef: " + aRef);
        
      } 
      public void end(){
        pRef = 0;
        vRef = 0;
        vMax = 100;
        aMax = 100;
        currState = trapState.accState;
      }
      
}
