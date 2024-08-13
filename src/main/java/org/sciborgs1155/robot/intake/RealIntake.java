package org.sciborgs1155.robot.intake;

import static org.sciborgs1155.robot.Ports.Intake.*;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class RealIntake implements IntakeIO {

  private final TalonFX leftMotor, rightMotor, wristMotor;
  
  private final Solenoid leftJaw, rightJaw;

  public RealIntake() {
    leftMotor = new TalonFX(INTAKE_LEFT);
    leftMotor.setInverted(true);
    leftMotor.setPosition(0);

    rightMotor = new TalonFX(INTAKE_RIGHT);
    rightMotor.setPosition(0);

    wristMotor = new TalonFX(INTAKE_WRIST);
    wristMotor.setPosition(0);

    leftJaw = new Solenoid(PneumaticsModuleType.REVPH, JAW_LEFT);
    rightJaw = new Solenoid(PneumaticsModuleType.REVPH, JAW_RIGHT);
  }


}
