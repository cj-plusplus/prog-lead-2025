// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.SolenoidSubsystem;

public class ReactToColor extends Command {
  
  private final ColorSensorSubsystem m_colorSensorSubsystem;
  private final SolenoidSubsystem m_solenoidSubsystem;
  private final MotorSubsystem m_motorSubsystem;

  public ReactToColor(ColorSensorSubsystem colorSensorSubsystem, SolenoidSubsystem solenoidSubsystem, MotorSubsystem motorSubsystem) {
    // DetectColor uses all subsystems
    m_colorSensorSubsystem = colorSensorSubsystem;
    m_solenoidSubsystem = solenoidSubsystem;
    m_motorSubsystem = motorSubsystem;
    addRequirements(colorSensorSubsystem, solenoidSubsystem, motorSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    // Continuously detects seen color
    String result = m_colorSensorSubsystem.detectColor();
    if (result == "red") {
      // Extend Solenoid
      m_solenoidSubsystem.extend();
      // Run motor for 5 seconds
      m_motorSubsystem.runMotor();
    } else if (result == "blue") {
      // Retract Solenoid
      m_solenoidSubsystem.retract();
      m_motorSubsystem.stopMotor();
    } else {
      // Do nothing 
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
