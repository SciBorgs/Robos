package org.sciborgs1155.robot.intake;

import monologue.Logged;

public interface IntakeIO extends AutoCloseable, Logged {

  /**
   * Sets the jaw to either open or closed.
   *
   * @param position A boolean representing the jaw state. Set as true for closing, and false for
   *     opening.
   */
  public void setJaw(boolean position);

  /**
   * Returns the detection of the beambreak.
   *
   * @return Whether the intake detects a cube in the intake.
   */
  public boolean hasCube();

  /**
   * Sets the intake power.
   *
   * @param portion A number between 0 and 1 representing the propotion of the max intake power
   *     used.
   */
  public void setPower(double portion); // portion from -1 to 1
}
