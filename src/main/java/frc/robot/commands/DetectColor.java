// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.SolenoidSubsystem;

public class DetectColor extends Command {
  
  private final ColorSensorSubsystem m_colorSensorSubsystem;
  private final SolenoidSubsystem m_solenoidSubsystem;
  private final MotorSubsystem m_motorSubsystem;

  public DetectColor(ColorSensorSubsystem colorSensorSubsystem, SolenoidSubsystem solenoidSubsystem, MotorSubsystem motorSubsystem) {
    // DetectColor uses all subsystems
    m_colorSensorSubsystem = colorSensorSubsystem;
    m_solenoidSubsystem = solenoidSubsystem;
    m_motorSubsystem = motorSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(colorSensorSubsystem, solenoidSubsystem, motorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    String result = m_colorSensorSubsystem.detectColor();
    if (result == "red") {
      m_solenoidSubsystem.extend();
      m_motorSubsystem.runMotor();
      // run motor for 5 seconds
    } else if (result == "blue") {
      m_solenoidSubsystem.retract();
      m_motorSubsystem.stopMotor();

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
