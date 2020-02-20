/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

	public static final int LEFT_MOTOR_1 = 0;
	public static final int LEFT_MOTOR_2 = 1;
	public static final int RIGHT_MOTOR_1 = 2;
	public static final int RIGHT_MOTOR_2 = 3;
	public static final int shifterDown1 = 1;
	public static final  int shifterUp1 = 0;
	public static final int shifterDown2 = 2;
	public static final  int shifterUp2 = 3;
	
	public static final int JOY_PORT = 0;
	public static final int WHEEL_PORT = 1;

	public static final double WHEEL_DIAMETER = 6;
	public static final double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
	public static final double TICKS_PER_INCH = 360 / CIRCUMFERENCE;
}

/*
1 ft --> -29276, -31923, -32453, -32864
*/