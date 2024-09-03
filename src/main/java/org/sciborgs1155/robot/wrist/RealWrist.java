package org.sciborgs1155.robot.wrist;

import static org.sciborgs1155.robot.Ports.Wrist.*;

import org.sciborgs1155.lib.FaultLogger;

import com.ctre.phoenix6.hardware.TalonFX;

public class RealWrist implements WristIO {
  public final TalonFX motor;

  /** Constructor. */
  public RealWrist() {
    motor = new TalonFX(INTAKE_WRIST);
    motor.setPosition(0);
    FaultLogger.register(motor);
  }

  @Override
  public void setVoltage(double voltage) {
    motor.setVoltage(voltage);
  }

  @Override
  public double getPosition() {
    return motor.getPosition().getValueAsDouble() * 2 * Math.PI;
  }

  @Override
  public double getVelocity() {
    return motor.getVelocity().getValueAsDouble() * 2 * Math.PI;
  }

  @Override
  public void close() throws Exception {
    motor.close();
  }
}
