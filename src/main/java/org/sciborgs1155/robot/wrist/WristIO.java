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
   * @return The position of the wrist in radians.
   */
  public double getPosition();

  /**
   * @return The velocity of the wrist in radians / sec.
   */
  public double getVelocity();
}
