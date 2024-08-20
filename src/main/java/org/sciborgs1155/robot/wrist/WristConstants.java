package org.sciborgs1155.robot.wrist;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Kilograms;
import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Mass;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Mult;

public class WristConstants {

  public static final Measure<Angle> MAX_ANGLE = Degrees.of(180);
  public static final Measure<Angle> MIN_ANGLE = Degrees.of(0);

  public static final Measure<Angle> STARTING_ANGLE =
      Degrees.of(0); // starting angle is when intake is tucked in the robot

  public static final Measure<Distance> LENGTH = Meters.of(0.5);
  // guess

  public static final Measure<Mult<Mult<Distance, Distance>, Mass>> MOI =
      (Meters).mult(Meters).mult(Kilograms).of(0.2);
  // bruh idk. 0.2 is probably a good enough estimate or somehting

  public static final double MOTOR_GEARING = 1.0;
  // idk this either. 1 should be fine
}
