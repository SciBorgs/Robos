package org.sciborgs1155.robot.wrist;

import static org.sciborgs1155.robot.Ports.Wrist.*;
import static org.sciborgs1155.robot.wrist.WristConstants.*;

import com.ctre.phoenix6.hardware.TalonFX;

public class RealWrist implements WristIO {
  public final TalonFX motor;

  public RealWrist() {
    motor = new TalonFX(INTAKE_WRIST);
    motor.setPosition(0);
  }

  @Override
  public void setVoltage(double voltage) {
    motor.setVoltage(voltage);
  }

  @Override
  public double getPosition() {
    return motor.getPosition().getValueAsDouble();
  }

  @Override
  public double getVelocity() {
    return motor.getVelocity().getValueAsDouble();
  }

  @Override
  public void close() throws Exception {
    motor.close();
  }
}
