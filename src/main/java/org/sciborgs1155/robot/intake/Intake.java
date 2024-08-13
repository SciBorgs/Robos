package org.sciborgs1155.robot.intake;

import static org.sciborgs1155.robot.Constants.*;

public class Intake {

  private IntakeIO hardware;

  public Intake(IntakeIO intake) {
    this.hardware = intake;
  }

  public static Intake create() {
    return isReal() ? new Intake(new RealIntake()) : new Intake(new SimIntake());
  }

  public static IntakeIO none() {
    return new Intake(new NoIntake());
  }
}
