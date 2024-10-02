package org.sciborgs1155.robot.elevator;

import static edu.wpi.first.units.Units.Inches;

public class NoElevator implements ElevatorIO {

  //should be zero I believe
  @Override
  public double getPosition() {
    return ElevatorConstants.MIN_HEIGHT.in(Inches);
  }

  @Override
  public double getVelocity() {
    return 0;
  }

  @Override
  public void setVoltage(double voltage) {}

  @Override
  public void close() {}
}
