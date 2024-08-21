package org.sciborgs1155.robot.forklift;

import monologue.Logged;

public interface ForkliftIO extends AutoCloseable, Logged {

  /**
   * Sets both pistons to a state.
   *
   * @param state the state. True for extended (forklift deployed) false for stowed.
   */
  public void set(boolean state);
}
