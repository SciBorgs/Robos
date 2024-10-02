package org.sciborgs1155.robot.elevator;

import static edu.wpi.first.units.Units.Kilograms;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Seconds;
import static org.sciborgs1155.robot.elevator.ElevatorConstants.*;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import org.sciborgs1155.robot.Constants;

public class SimElevator implements ElevatorIO {
  private ElevatorSim sim;

  public SimElevator() {
    sim =
        new ElevatorSim(
            //They use 775s
            DCMotor.getFalcon500(4),
            HIGH_GEAR,
            CARRIAGE_MASS.in(Kilograms),
            SPROCKET_RADIUS.in(Meters),
            MIN_HEIGHT.in(Meters),
            MAX_HEIGHT.in(Meters),
            true,
            //technically should be MIN_HEIGHT
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
    sim.update(Constants.PERIOD.in(Seconds));
  }

  @Override
  public void close() {}
}
