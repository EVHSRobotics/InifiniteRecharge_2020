/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class ShooterJoy extends CommandBase {
  Shooter shooter;
  Turret turret;
  double throttle;
  double inThrottle;
  double outThrottle;
  double turretThrottle;
  /**
   * Creates a new ShooterJoy.
   */
  public ShooterJoy(Shooter shooter, Turret turret) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
    this.shooter = shooter;
    System.out.println("shooter subsystem working");
    this.turret = turret;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("shooter speed", 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
   
    throttle = SmartDashboard.getNumber("shooter speed: ", 0);
    inThrottle = RobotContainer.joy.getRawAxis(1);
    outThrottle = RobotContainer.joy.getRawAxis(2);
    turretThrottle = RobotContainer.joy.getRawAxis(3);

  
    shooter.outtakeBall(outThrottle);
    shooter.inttakeBall(inThrottle);
    turret.turnTurret(turretThrottle);
    System.out.println("Limit Switch status: " + turret.getLeftLimitSwitchStatus());
    System.out.println("Right Limit Switch status: " + turret.getRightLimitSwitchStatus());
 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
