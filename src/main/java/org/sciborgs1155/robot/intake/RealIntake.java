package org.sciborgs1155.robot.intake;

import static org.sciborgs1155.robot.Ports.Intake.*;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

import static org.sciborgs1155.lib.FaultLogger.*;

public class RealIntake implements IntakeIO {

  private final TalonFX leftMotor, rightMotor;
  private final Solenoid leftJaw, rightJaw;

  private DigitalInput leftSensor = new DigitalInput(LEFT_SENSOR);
  private DigitalInput rightSensor = new DigitalInput(RIGHT_SENSOR);

  public RealIntake() {
    leftMotor = new TalonFX(INTAKE_LEFT);
    leftMotor.setInverted(true);
    leftMotor.setPosition(0);
    register(leftMotor);
    
    rightMotor = new TalonFX(INTAKE_RIGHT);
    rightMotor.setPosition(0);
    register(rightMotor);

    leftJaw = new Solenoid(PneumaticsModuleType.REVPH, JAW_LEFT);
    rightJaw = new Solenoid(PneumaticsModuleType.REVPH, JAW_RIGHT);
  }

  @Override
  public void setPower(double portion) {
    leftMotor.set(portion);
    rightMotor.set(portion);
  }

  @Override
  public void setJaw(boolean position) {
    leftJaw.set(position);
    rightJaw.set(position);
  }

  @Override
  public boolean hasCube() {
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
