// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensorSubsystem extends SubsystemBase {
  
  private final Port i2c;
  private final ColorSensorV3 m_colorSensor;
  private final ColorMatch m_colorMatch;

  private final Color kRedGamePiece;
  private final Color kBlueGamePiece;


  
  public ColorSensorSubsystem() {
    // Uses onboard I2C port
    i2c = Port.kOnboard;
    // Initialize Color Sensor, which uses the I2C port
    m_colorSensor = new ColorSensorV3(i2c);
    // Initialize Color Match Object
    m_colorMatch = new ColorMatch();
    /*
   * Creates target values for red and blue. These look for exactly red and
   * exactly blue, and in real life, they would likely be calibrated
   * based on the color sensor's readings of the game pieces.
   */
    kRedGamePiece = new Color(1,0,0);
    kBlueGamePiece = new Color(0,0,1);
  }

  @Override
  public void periodic() {
  }

  public String detectColor() {
    // Gets color the color sensor is currently seeing
    Color detectedColor = m_colorSensor.getColor();
    /*
     * Returns the color values of kRedGamePiece or kBlueGamePiece if the color
     * matches
     * with an confidence value of 95% or greater. Otherwise, returns a null color
     * value.
     */
    ColorMatchResult result = m_colorMatch.matchClosestColor(detectedColor);

    /*
     * Returns "red" or "blue" if color sensor is 95% or more confident that it sees one
     * of those colors. Otherwise, returns "none".
     */
    if (result.color == kRedGamePiece) { return "red"; }
    else if (result.color == kBlueGamePiece) { return "blue"; }
    else { return "none"; } 
  }
}
