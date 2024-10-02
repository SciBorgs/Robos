package org.sciborgs1155.robot.wrist;

import static edu.wpi.first.units.Units.Radians;

public class NoWrist implements WristIO {

  @Override
  public void setVoltage(double voltage) {}

  // should still be zero
  @Override
  public double getPosition() {
    return WristConstants.MIN_ANGLE.in(Radians);
  }

  @Override
  public double getVelocity() {
    return 0;
  }

  @Override
  public void close() {}
}
