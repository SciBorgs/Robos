package org.sciborgs1155.robot.wrist;

import static edu.wpi.first.units.Units.Kilograms;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Radians;
import static org.sciborgs1155.robot.wrist.WristConstants.*;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class SimWrist implements WristIO {

  private final SingleJointedArmSim sim =
      new SingleJointedArmSim(
          LinearSystemId.createSingleJointedArmSystem(
              DCMotor.getFalcon500(1),
              MOI.in((Meters).mult(Meters).mult(Kilograms)),
              1.0 / MOTOR_GEARING),
          DCMotor.getFalcon500(1),
          1.0 / MOTOR_GEARING,
          LENGTH.in(Meters),
          MIN_ANGLE.in(Radians),
          MAX_ANGLE.in(Radians),
          true,
          STARTING_ANGLE.in(Radians));

  @Override
  public void setVoltage(double voltage) {
    sim.setInputVoltage(voltage);
  }

  @Override
  public double getPosition() {
    return sim.getAngleRads();
  }

  @Override
  public double getVelocity() {
    return sim.getVelocityRadPerSec();
  }

  @Override
  public void close() {}
}
