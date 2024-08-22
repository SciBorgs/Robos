package org.sciborgs1155.robot.elevator;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Kilograms;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Second;

import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Mass;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Velocity;

public class ElevatorConstants {

  public static final Measure<Distance> MIN_HEIGHT = Inches.of(0);
  public static final Measure<Distance> MAX_HEIGHT = Inches.of(100000 / 1271);

  // i guessed this one
  public static final Measure<Mass> CARRIAGE_MASS = Kilograms.of(20);

  // 2 sounds right
  public static final Measure<Distance> SPROCKET_RADIUS = Inches.of(2);

  // we're not going for low gear. too lazy bo bazy
  public static final double HIGH_GEAR = 12.06;

  // made up numbers :D
  public static final Measure<Velocity<Distance>> MAX_VELOCITY = MetersPerSecond.of(10);
  public static final Measure<Velocity<Velocity<Distance>>> MAX_ACCEL =
      MetersPerSecond.of(10).per(Second);

  public static final double kP = 15;
  public static final double kI = 0;
  public static final double kD = 0.5;

  public static final double kS = 0.5;
  public static final double kV = 1;
  public static final double kA = 0;
  public static final double kG = 0.58;
}
