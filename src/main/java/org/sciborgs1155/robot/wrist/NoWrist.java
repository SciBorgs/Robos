package org.sciborgs1155.robot.wrist;

import static edu.wpi.first.units.Units.Radians;

import edu.wpi.first.units.Current;
import edu.wpi.first.units.Measure;

public class NoWrist implements WristIO {

  @Override
  public void setVoltage(double voltage) {}

  @Override
  public void setCurrentLimit(Measure<Current> limit) {}

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
