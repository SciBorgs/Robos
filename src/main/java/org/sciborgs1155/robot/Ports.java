package org.sciborgs1155.robot;

public final class Ports {
  public static final class OI {
    public static final int OPERATOR = 0;
    public static final int DRIVER = 1;
  }

  public static final class Drive {
    public static final int FRONT_LEFT_DRIVE = 11;
    public static final int REAR_LEFT_DRIVE = 10;
    public static final int FRONT_RIGHT_DRIVE = 12;
    public static final int REAR_RIGHT_DRIVE = 13;

    public static final int FRONT_LEFT_TURNING = 15;
    public static final int REAR_LEFT_TURNING = 14;
    public static final int FRONT_RIGHT_TURNING = 16;
    public static final int REAR_RIGHT_TURNING = 17;
  }

  public static final class Intake {
    public static final int INTAKE_LEFT = 18;
    public static final int INTAKE_RIGHT = 19;

    public static final int JAW_LEFT = 24;
    public static final int JAW_RIGHT = 25;

    public static final int LEFT_SENSOR = 26;
    public static final int RIGHT_SENSOR = 27;
  }

  public static final class Wrist {
    public static final int INTAKE_WRIST = 28;
  }

  public static final class Elevator {
    public static final int FIRST_MOTOR = 20;
    public static final int SECOND_MOTOR = 21;
    public static final int THIRD_MOTOR = 22;
    public static final int FOURTH_MOTOR = 23;
  }

  public static final class Hanger {
    public static final int LEFT_PISTON = 29;
    public static final int RIGHT_PISTON = 30;
  }

  public static final class Forklift {
    public static final int PISTON = 31;
  }
}
