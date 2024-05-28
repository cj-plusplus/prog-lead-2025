// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MotorSubsystem extends SubsystemBase {
  private final CANSparkMax motor;
  private final Timer timer;
  public MotorSubsystem() {
    /*
   * Init variable for motor controller. The NEO brushless motor uses a CAN
   * SparkMax motor controler, as both are made by REV Robotics for use with each
   * other.
   * This motor controllor uses a CAN ID of 5.
   */
    motor = new CANSparkMax(Constants.kMotorCANPort, MotorType.kBrushless);

    // Timer used to stop motor after a specified period of time
    timer = new Timer();
  }

  public void runMotor() {
    // Motor is run at full speed
    motor.set(1);
    // Timer is started
    timer.start();
  }

  public void stopMotor() {
    // Motor is stopped
    motor.stopMotor();
  }
  @Override
  public void periodic() {

    // Checks if target time (5 seconds) has passed
    if (timer.hasElapsed(Constants.desiredTime)) {

      /*
       * Motor is stopped, and timer is stopped and reset so that it can be reused
       * in the future. This means that as long as the color sensor sees red,
       * the motor will continue to run in 5-second intervals.
       */
      motor.stopMotor();
      timer.stop();
      timer.reset();
    }
  }
}
