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
  public static final Measure<Mass> CARRIAGE_MASS = Kilograms.of(16);

  // 2 sounds right
  public static final Measure<Distance> SPROCKET_RADIUS = Inches.of(2);

  // we're not going for low gear. too lazy bo bazy
  public static final double HIGH_GEAR = 12.06;

  // made up numbers :D
  public static final Measure<Velocity<Distance>> MAX_VELOCITY = MetersPerSecond.of(1);
  public static final Measure<Velocity<Velocity<Distance>>> MAX_ACCEL =
      MetersPerSecond.of(1).per(Second);

  // uhhh these sounded right lmao
  public static final double kP = 0.1;
  public static final double kI = 0;
  public static final double kD = 0.01;

  // I TOOK THESE FROM SCIBORGS 2023!!!!!!!
  public static final double kS = 0.4;
  public static final double kV = 0.07;
  public static final double kA = 33.25;
  public static final double kG = 1.5;
}
