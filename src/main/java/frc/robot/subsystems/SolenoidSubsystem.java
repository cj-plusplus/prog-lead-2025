// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SolenoidSubsystem extends SubsystemBase {

  
  private final DoubleSolenoid m_doubleSolenoid;

  public SolenoidSubsystem() {
   /*
   * Init variable for DoubleSolenoid. Uses the CTR Electronics Pneumatic Control
   * Module,
   * and uses channels 6 and 7 (last available channels) on the module for forward
   * and reverse, respectively
   */
    m_doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.kSolenoidForwardPort, Constants.kSolenoidReversePort);
  }

  public void extend() {
    // Sets solenoid to extend (sends power to channel 6)
    m_doubleSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void retract() {
    // Sets solenoid to retract (sends power to channel 7)
    m_doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  @Override
  public void periodic() {

  }
}
