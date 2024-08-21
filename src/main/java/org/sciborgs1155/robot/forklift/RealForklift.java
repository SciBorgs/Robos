package org.sciborgs1155.robot.forklift;

import static org.sciborgs1155.robot.Ports.Forklift.*;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class RealForklift implements ForkliftIO {
  private final Solenoid piston;

  /** Constructor */
  public RealForklift() {
    piston = new Solenoid(PneumaticsModuleType.REVPH, PISTON);
  }

  @Override
  public void set(boolean state) {
    piston.set(state);
  }

  @Override
  public void close() {
    piston.close();
  }
}
