// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class Robot extends TimedRobot {

  /*
   * Init variable for DoubleSolenoid. Uses the CTR Electronics Pneumatic Control
   * Module,
   * and uses channels 6 and 7 (last available channels) on the module for forward
   * and reverse, respectively
   */
  private final DoubleSolenoid m_doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);

  /*
   * Init variable for motor controller. The NEO brushless motor uses a CAN
   * SparkMax motor controler, as both are made by REV Robotics for use with each
   * other.
   * This motor controllor uses a CAN ID of 1, as the CTRE PCM defaults to a CAN
   * ID of 0.
   */
  private final CANSparkMax m_CANSparkMax = new CANSparkMax(1, MotorType.kBrushless);

  // Init variable for I2C port
  private final Port i2cPort = I2C.Port.kOnboard;

  // Init variable for REV Color Sensor V3
  private final ColorSensorV3 m_ColorSensorV3 = new ColorSensorV3(i2cPort);

  // Init variable for REV Color Match
  private final ColorMatch m_ColorMatch = new ColorMatch();
  /*
   * Creates target values for red and blue. These look for exactly red and
   * exactly blue, and in real life, they would likely be calibrated
   * based on the color sensor's readings of the game pieces.
   */
  private final Color kRedGamePiece = new Color(1, 0, 0);
  private final Color kBlueGamePiece = new Color(0, 0, 1);

  // Creates variable for a timer (used for motors later)
  private Timer timer;

  @Override
  public void robotInit() {
    // Initializes variable for a timer (used for motors later)
    timer = new Timer();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {

    // Gets color the color sensor is currently seeing
    Color detectedColor = m_ColorSensorV3.getColor();

    /*
     * Returns the color values of kRedGamePiece or kBlueGamePiece if the color
     * matches
     * with an confidence value of 95% or greater. Otherwise, returns a null color
     * value.
     */
    ColorMatchResult result = m_ColorMatch.matchClosestColor(detectedColor);

    if (result.color == kRedGamePiece) {
      // Extends the solenoid
      m_doubleSolenoid.set(DoubleSolenoid.Value.kForward);
      // Sets the motor to full power
      m_CANSparkMax.set(1.0);
      // Starts the timer, which will stop the motor after 5 seconds have elapsed.
      // Refer to the code under this if/else block for more details.
      timer.start();
    } else if (result.color == kBlueGamePiece) {
      // Retracts the solenoid
      m_doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
      // Immediately stops the motor
      m_CANSparkMax.stopMotor();
    } else {
      // Do nothing.
    }

    /*
     * Checks if timer has passed the desired time of 5 seconds yet, and if it has,
     * immediately stops the motor and resets the timer
     */
    if (timer.hasElapsed(5)) {
      // Stops the timer.
      timer.stop();
      // Resets the timer so that it can be reused the next time the color red is
      // seen.
      timer.reset();
      // Stops the motor.
      m_CANSparkMax.stopMotor();
      ;
    }
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic() {
  }
}
