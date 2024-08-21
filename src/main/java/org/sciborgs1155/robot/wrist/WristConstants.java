package org.sciborgs1155.robot.wrist;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Kilograms;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.Second;

import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Mass;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Mult;
import edu.wpi.first.units.Velocity;

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

  public static final double MOTOR_GEARING = 20.0;
  // idk this either. 20 should be fine

  public static final Measure<Velocity<Velocity<Angle>>> MAX_ACCEL =
      RadiansPerSecond.per(Second).of(1);
  public static final Measure<Velocity<Angle>> MAX_VELOCITY = RadiansPerSecond.of(1);

  // i just stole this one from TFC
  public static final Measure<Angle> POSITION_TOLERANCE = Degrees.of(0.8);

  // mad guess
  public static final double kP = 0.1;
  public static final double kI = 0;
  public static final double kD = 0.01;

  // idk how to get these
  public static final double kS = 0.1;
  public static final double kV = 1.5;
  public static final double kA = 0.01;
  public static final double kG = 0.1;
}
