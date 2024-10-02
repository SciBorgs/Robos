package org.sciborgs1155.robot.intake;

import static org.sciborgs1155.lib.FaultLogger.*;
import static org.sciborgs1155.robot.Ports.Intake.*;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class RealIntake implements IntakeIO {

  private final TalonFX leftMotor, rightMotor;
  private final Solenoid leftJaw, rightJaw;

  private DigitalInput leftSensor = new DigitalInput(LEFT_SENSOR);
  private DigitalInput rightSensor = new DigitalInput(RIGHT_SENSOR);

  public RealIntake() {
    leftMotor = new TalonFX(INTAKE_LEFT);
    leftMotor.setInverted(true);
    // uneeded
    leftMotor.setPosition(0);
    register(leftMotor);

    // right motor does not follow left, see TalonFX.setControl()
    rightMotor = new TalonFX(INTAKE_RIGHT);
    // uneeded
    rightMotor.setPosition(0);
    register(rightMotor);

    // CTRE PCM
    leftJaw = new Solenoid(PneumaticsModuleType.REVPH, JAW_LEFT);
    rightJaw = new Solenoid(PneumaticsModuleType.REVPH, JAW_RIGHT);
  }

  @Override
  public void setPower(double portion) {
    // again a follower pattern is way safer
    leftMotor.set(portion);
    rightMotor.set(portion);
  }

  // position is an interesting name
  @Override
  public void setJaw(boolean position) {
    leftJaw.set(position);
    rightJaw.set(position);
  }

  @Override
  public boolean hasCube() {
    // erm you should read up on DeMorgan's law
    return !(leftSensor.get() && rightSensor.get());
    // definitely has cube, both left and right sensor detect
  }

  @Override
  public void close() {
    leftMotor.close();
    rightMotor.close();

    leftJaw.close();
    rightJaw.close();

    leftSensor.close();
    rightSensor.close();
  }
}
