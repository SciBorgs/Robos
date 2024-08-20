package org.sciborgs1155.robot.hanger;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

import static org.sciborgs1155.robot.Ports.Hanger.*;

public class RealHanger implements HangerIO {

    private final Solenoid leftPiston, rightPiston;

    public RealHanger() {
        leftPiston = new Solenoid(PneumaticsModuleType.REVPH, LEFT_PISTON);
        rightPiston = new Solenoid(PneumaticsModuleType.REVPH, RIGHT_PISTON);
    }

    @Override
    public void retract() {
        leftPiston.set(false);
        rightPiston.set(false);
    }
    
    @Override
    public void close() {
        leftPiston.close();
        rightPiston.close();
    }
}
