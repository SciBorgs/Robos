package org.sciborgs1155.robot.elevator;

import static edu.wpi.first.units.Units.Meters;
import static org.sciborgs1155.robot.Ports.Elevator.*;
import static org.sciborgs1155.robot.elevator.ElevatorConstants.HIGH_GEAR;
import static org.sciborgs1155.robot.elevator.ElevatorConstants.SPROCKET_RADIUS;

import com.ctre.phoenix6.hardware.TalonFX;
import java.util.List;

public class RealElevator implements ElevatorIO {
  private TalonFX lead;
  private TalonFX rightFollower;
  private TalonFX leftFollowerOne;
  private TalonFX leftFollowerTwo;

  /* Rotations to meters conversion factor */
  private static double ROTATION_TO_METERS = 2 * Math.PI * SPROCKET_RADIUS.in(Meters) * HIGH_GEAR;

  public RealElevator() {
    lead = new TalonFX(FIRST_MOTOR);
    lead.setInverted(true);

    rightFollower = new TalonFX(SECOND_MOTOR);
    rightFollower.setInverted(true);

    leftFollowerOne = new TalonFX(THIRD_MOTOR);
    leftFollowerOne.setInverted(false);

    leftFollowerTwo = new TalonFX(FOURTH_MOTOR);
    leftFollowerOne.setInverted(false);
  }

  @Override
  public double getPosition() {
    return lead.getPosition().getValueAsDouble() * ROTATION_TO_METERS;
  }

  @Override
  public double getVelocity() {
    return lead.getVelocity().getValueAsDouble() * ROTATION_TO_METERS;
  }

  @Override
  public void setVoltage(double voltage) {
    lead.setVoltage(voltage);
  }

  @Override
  public void close() {
    for (TalonFX motor : List.of(lead, rightFollower, leftFollowerOne, leftFollowerTwo)) {
      motor.close();
    }
    // pretty fancy huh :)
  }
}
