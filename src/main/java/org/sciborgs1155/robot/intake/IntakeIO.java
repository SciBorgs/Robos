package org.sciborgs1155.robot.intake;

public interface IntakeIO {
  public void closeJaw();

  public void openJaw();

  public boolean jawClosed();

  public boolean hasCube();

  public void setIntakeVoltage();

  public double getIntakeVelocity();

  public voic setWristVoltage();

  public Rotation2d getWristAngle();
}
