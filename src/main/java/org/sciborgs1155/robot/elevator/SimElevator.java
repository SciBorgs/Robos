package org.sciborgs1155.robot.elevator;

import static edu.wpi.first.units.Units.Kilograms;
import static edu.wpi.first.units.Units.Meters;
import static org.sciborgs1155.robot.elevator.ElevatorConstants.*;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;

public class SimElevator implements ElevatorIO {
  private ElevatorSim sim;

  public SimElevator() {
    sim =
        new ElevatorSim(
            DCMotor.getFalcon500(4),
            HIGH_GEAR,
            CARRIAGE_MASS.in(Kilograms),
            SPROCKET_RADIUS.in(Meters),
            MIN_HEIGHT.in(Meters),
            MAX_HEIGHT.in(Meters),
            true,
            0);
  }

  @Override
  public double getPosition() {
    return sim.getPositionMeters();
  }

  @Override
  public double getVelocity() {
    return sim.getVelocityMetersPerSecond();
  }

  @Override
  public void setVoltage(double voltage) {
    sim.setInputVoltage(voltage);
  }

  @Override
  public void close() {}
}
