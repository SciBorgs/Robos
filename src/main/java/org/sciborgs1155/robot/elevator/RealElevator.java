package org.sciborgs1155.robot.elevator;

import static org.sciborgs1155.robot.Ports.Elevator.*;

import com.ctre.phoenix6.hardware.TalonFX;

public class RealElevator implements ElevatorIO {
  TalonFX lead;
  TalonFX rightFollower;
  TalonFX leftFollowerOne;
  TalonFX leftFollowerTwo;

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
}
