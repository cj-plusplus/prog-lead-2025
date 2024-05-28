// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.DetectColor;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.SolenoidSubsystem;


public class RobotContainer {
  // Initializes variables all 3 subsystems used in this project
  private final MotorSubsystem m_motorSubsystem;
  private final SolenoidSubsystem m_solenoidSubsystem;
  private final ColorSensorSubsystem m_colorSensorSubsystem;


  


  public RobotContainer() {
    // Defines variables all 3 subsystems used in this project
    m_motorSubsystem = new MotorSubsystem();
    m_solenoidSubsystem = new SolenoidSubsystem();
    m_colorSensorSubsystem = new ColorSensorSubsystem();
    
    configureBindings();
  }

 
  private void configureBindings() {}

  public void teleopInit() {
    Commands.run(() -> new DetectColor(m_colorSensorSubsystem,m_solenoidSubsystem,m_motorSubsystem),m_colorSensorSubsystem,m_solenoidSubsystem,m_motorSubsystem);
  }
}
