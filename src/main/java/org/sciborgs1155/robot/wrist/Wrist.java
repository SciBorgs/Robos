package org.sciborgs1155.robot.wrist;

import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.Second;
import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Volts;
import static edu.wpi.first.wpilibj2.command.button.RobotModeTriggers.autonomous;
import static edu.wpi.first.wpilibj2.command.button.RobotModeTriggers.teleop;
import static org.sciborgs1155.robot.Constants.PERIOD;
import static org.sciborgs1155.robot.wrist.WristConstants.*;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Measure;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import monologue.Logged;
import org.sciborgs1155.robot.Robot;

public class Wrist extends SubsystemBase implements Logged, AutoCloseable {
  private final WristIO hardware;
  private final SysIdRoutine sysIdRoutine;

  private final ProfiledPIDController pid =
      new ProfiledPIDController(
          kP, kI, kD, new TrapezoidProfile.Constraints(MAX_VELOCITY, MAX_ACCEL));

  private final ArmFeedforward feedforward = new ArmFeedforward(kS, kV, kA, kG);

  /**
   * Constructor.
   *
   * @param hardware IntakeIO representing the hardware.
   */
  private Wrist(WristIO hardware) {
    this.hardware = hardware;

    sysIdRoutine =
        new SysIdRoutine(
            new SysIdRoutine.Config(Volts.per(Second).of(0.5), Volts.of(3), Seconds.of(6)),
            new SysIdRoutine.Mechanism(v -> hardware.setVoltage(v.in(Volts)), null, this));

    pid.reset(MIN_ANGLE.in(Radians));

    teleop().or(autonomous()).onTrue(runOnce(() -> pid.reset(hardware.getPosition())));
  }

  /**
   * Creates a new real wrist if the robot is real, and creates a new sim wrist if the robot is not real.
   * @return A new Wrist.
   */
  public static Wrist create() {
    return Robot.isReal() ? new Wrist(new RealWrist()) : new Wrist(new SimWrist());
  }

  /**
   * Creates a new none wrist
   * @return A new none wrist.
   */
  public static Wrist none() {
    return new Wrist(new NoWrist());
  }

  /**
   * Extends the wrist to its maximum angle.
   * @return A command to extend the wrist to its max angle.
   */
  public Command extend() {
    return runWrist(() -> MAX_ANGLE.in(Radians));
  }

  /**
   * Retracts the wrist to its minimum angle.
   * @return A command to retract the wrist to its min angle.
   */
  public Command retract() {
    return runWrist(() -> MIN_ANGLE.in(Radians));
  }

  /**
   * Moves the wrist to the goal angle.
   * 
   * @param goalAngle A double supplier for the goal angle in radians.
   * @return A command which moves the wrist to the goal angle.
   */
  public Command runWrist(DoubleSupplier goalAngle) {
    return run(() -> nextVoltage(goalAngle.getAsDouble())).asProxy();
  }

  /**
   * Finds the next voltage to angle the wrist, and then 
   * angles the wrist towards that angle.
   *
   * @param goalAngle the angle to move the wrist to, in radians.
   */
  public void nextVoltage(double goalAngle) {
    double goal =
        !Double.isNaN(goalAngle)
            ? MathUtil.clamp(goalAngle, MIN_ANGLE.in(Radians), MAX_ANGLE.in(Radians))
            : MIN_ANGLE.in(Radians);
    TrapezoidProfile.State prevSet = pid.getSetpoint();
    double fb = pid.calculate(hardware.getPosition(), goal);
    double accel = (pid.getSetpoint().velocity - prevSet.velocity) / PERIOD.in(Seconds);
    double ff =
        feedforward.calculate(
            pid.getSetpoint().position + Math.PI, pid.getSetpoint().velocity, accel);
    hardware.setVoltage(fb + ff);
  }

  public Command dynamicForward() {
    return sysIdRoutine.dynamic(Direction.kForward).until(() -> hardware.getPosition() > MAX_ANGLE.in(Radians) - 0.2);
  }

  public Command dynamicBackward() {
    return sysIdRoutine.dynamic(Direction.kReverse).until(() -> hardware.getPosition() < MIN_ANGLE.in(Radians) + 0.2);
  }

  public Command quasistaticForward() {
    return sysIdRoutine.quasistatic(Direction.kForward).until(() -> hardware.getPosition() > MAX_ANGLE.in(Radians) - 0.2);
  }

  public Command quasistaticBackward() {
    return sysIdRoutine.quasistatic(Direction.kReverse).until(() -> hardware.getPosition() < MIN_ANGLE.in(Radians) + 0.2);
  }

  /**
   * The position of the wrist.
   * @return The wrist's position in radians.
   */
  public double position() {
    return hardware.getPosition();
  }

  /**
   * Finds whether the PID is at its goal or not.
   * @return whether the PID is at it's goal.
   */
  public boolean atGoal() {
    return pid.atGoal();
  }

  /**
   * Finds whether the wrist is close to a certain position or not.
   * @param position the position being tested, in radians
   * @return Whether the wrist is close enough to the position.
   */
  public boolean atPosition(double position) {
    return Math.abs(position - hardware.getPosition()) < POSITION_TOLERANCE.in(Radians);
  }

  @Override
  public void close() throws Exception {
    hardware.close();
  }
}
