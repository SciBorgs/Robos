package org.sciborgs1155.robot.elevator;

import monologue.Logged;

//I think they have a limit switch to zero position but not strictly necessary
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
