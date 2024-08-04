package org.sciborgs1155.robot.intake;

import com.ctre.phoenix6.hardware.TalonFX;

import static org.sciborgs1155.robot.Ports.Intake.*;

public class RealIntake implements IntakeIO {
    
    private final TalonFX leftMotor = new TalonFX(INTAKE_LEFT);
    private final TalonFX rightMotor = new TalonFX(INTAKE_RIGHT);

    public RealIntake() {
        leftMotor.setInverted(true);

    }
}
