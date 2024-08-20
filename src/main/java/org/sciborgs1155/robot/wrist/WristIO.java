package org.sciborgs1155.robot.wrist;

import monologue.Logged;

public interface WristIO extends AutoCloseable, Logged {

  /**
   * Sets the voltage of the wrist.
   *
   * @param voltage The voltage of the wrist.
   */
  public void setVoltage(double voltage);

  /**
   * Gets the position of the wrist.
   *
   * @return The position of the wrist in radians.
   */
  public double getPosition();

  /**
   * Gets the velocity of the wrist.
   *
   * @return The velocity of the wrist in radians / second.
   */
  public double getVelocity();
}
