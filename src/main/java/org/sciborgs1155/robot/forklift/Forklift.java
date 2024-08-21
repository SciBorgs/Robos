package org.sciborgs1155.robot.forklift;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.Logged;
import org.sciborgs1155.robot.Robot;

public class Forklift extends SubsystemBase implements AutoCloseable, Logged {

  private ForkliftIO hardware;

  /**
   * Constructor.
   *
   * @param hardware The hardware being used.
   */
  private Forklift(ForkliftIO hardware) {
    this.hardware = hardware;
  }

  /**
   * Creates a new Forklift.
   *
   * @return A Forklift class with a RealForklift if the robot is real, and a NoForklift if the
   *     robot is not real.
   */
  public static Forklift create() {
    return Robot.isReal() ? new Forklift(new RealForklift()) : new Forklift(new NoForklift());
  }

  /**
   * Creates a new Forklift.
   *
   * @return A Forklift class with a NoForklift hardware.
   */
  public static Forklift none() {
    return new Forklift(new NoForklift());
  }

  @Override
  public void close() throws Exception {
    hardware.close();
  }
}
