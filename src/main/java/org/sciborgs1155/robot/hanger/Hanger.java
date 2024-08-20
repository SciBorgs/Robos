package org.sciborgs1155.robot.hanger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.Logged;
import org.sciborgs1155.robot.Robot;

public class Hanger extends SubsystemBase implements AutoCloseable, Logged {

  private HangerIO hardware;

  private Hanger(HangerIO hardware) {
    this.hardware = hardware;
  }

  public static Hanger create() {
    return Robot.isReal() ? new Hanger(new RealHanger()) : new Hanger(new NoHanger());
  }

  public static Hanger none() {
    return new Hanger(new NoHanger());
  }

  public Command activate() {
    return runOnce(hardware::retract);
  }

  @Override
  public void close() throws Exception {
    hardware.close();
  }
}
