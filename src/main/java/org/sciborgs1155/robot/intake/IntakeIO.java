package org.sciborgs1155.robot.intake;

public interface IntakeIO {
  public void closeJaw();

  public void openJaw();

  public boolean jawClosed();

  public boolean hasCube();

  public void setIntakePower(double portion); //portion from -1 to 1

  public void setWristVoltage(double voltage);

  public Rotation2d getWristAngle();
}
