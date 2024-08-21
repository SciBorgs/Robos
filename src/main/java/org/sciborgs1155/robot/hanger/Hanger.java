package org.sciborgs1155.robot.hanger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.Logged;
import org.sciborgs1155.robot.Robot;

public class Hanger extends SubsystemBase implements AutoCloseable, Logged {

  private HangerIO hardware;

  /**
   * Constructor.
   *
   * @param hardware A
   */
  private Hanger(HangerIO hardware) {
    this.hardware = hardware;
  }

  /**
   * Creates a new Hanger.
   *
   * @return A Hanger class with a RealHanger if the robot is real, and a NoHanger if the robot is
   *     not real.
   */
  public static Hanger create() {
    return Robot.isReal() ? new Hanger(new RealHanger()) : new Hanger(new NoHanger());
  }

  /**
   * Creates a new Hanger
   *
   * @return A Hanger class with a NoHanger hardware.
   */
  public static Hanger none() {
    return new Hanger(new NoHanger());
  }

  /**
   * Deploys the hanger by retracting the pistons.
   *
   * @return A command to deploy the hanger.
   */
  public Command activate() {
    return runOnce(hardware::retract);
  }

  @Override
  public void close() throws Exception {
    hardware.close();
  }
}
