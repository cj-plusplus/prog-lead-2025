// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
  // CANSparkMax runs on CAN port 5
  public static final int kMotorCANPort = 5;

  // Ending channels on CTRE PCM (6 and 7) are used for double solenoid's forward and reverse ports
  public static final int kSolenoidForwardPort = 6;
  public static final int kSolenoidReversePort = 7;

  // Motor runs for 5 seconds
  public static final int desiredTime = 5;
}
