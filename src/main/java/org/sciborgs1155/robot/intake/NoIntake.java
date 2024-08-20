package org.sciborgs1155.robot.intake;

public class NoIntake implements IntakeIO {

  @Override
  public void setJaw(boolean position) {}

  @Override
  public boolean hasCube() {
    return false;
  }

  @Override
  public void setPower(double portion) {
    // nothing goes here hehah
  }

  @Override
  public void close() {}
}
