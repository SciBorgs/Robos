package org.sciborgs1155.robot.intake;

import monologue.Logged;

public interface IntakeIO extends AutoCloseable, Logged {
  /*
   * as you may observe in the source code, technically you have three states:
   *  both pistons activated, one activated, and no activated.
   * One piston activated is how wide you want it to be as wide as the cube (while intaking)
   * Two tightly clamps the cube
   * None will drop the cube (note this won't be acceptable for intaking the cube)
   */

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
