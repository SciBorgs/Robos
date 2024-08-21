package org.sciborgs1155.robot.elevator;

import monologue.Logged;

public interface ElevatorIO extends AutoCloseable, Logged {

  /**
   * @return The position of the Elevator in meters.
   */
  public double getPosition();

  /**
   * @return The current velocity of the Elevator motors in meters / sec.
   */
  public double getVelocity();

  /**
   * Sets the voltage of the Elevator motors.
   *
   * @param voltage The voltage of the Elevator motors.
   */
  public void setVoltage(double voltage);
}
