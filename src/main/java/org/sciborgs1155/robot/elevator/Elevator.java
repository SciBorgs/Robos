package org.sciborgs1155.robot.elevator;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Seconds;
import static org.sciborgs1155.robot.Constants.PERIOD;
import static org.sciborgs1155.robot.elevator.ElevatorConstants.*;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.Set;
import monologue.Annotations.Log;
import monologue.Logged;
import org.sciborgs1155.lib.Assertion;
import org.sciborgs1155.lib.Assertion.EqualityAssertion;
import org.sciborgs1155.lib.Test;
import org.sciborgs1155.robot.Robot;

public class Elevator extends SubsystemBase implements AutoCloseable, Logged {
  private ElevatorIO hardware;

  private final ElevatorFeedforward ff = new ElevatorFeedforward(kS, kG, kV, kA);
  private final ProfiledPIDController pid =
      new ProfiledPIDController(
          kP, kI, kD, new TrapezoidProfile.Constraints(MAX_VELOCITY, MAX_ACCEL));

  private double setpoint = 0;

  /**
   * Constructor.
   *
   * @param hardware IntakeIO representing the hardware.
   */
  private Elevator(ElevatorIO hardware) {
    this.hardware = hardware;
  }

  /**
   * Creates a new real elevator if the robot is real, and creates a new sim elevator if the robot
   * is not real.
   *
   * @return A new Elevator.
   */
  public static Elevator create() {
    return Robot.isReal() ? new Elevator(new RealElevator()) : new Elevator(new SimElevator());
  }

  /**
   * Creates a new none elevator.
   *
   * @return A new none elevator.
   */
  public static Elevator none() {
    return new Elevator(new NoElevator());
  }

  /**
   * Moves to the top of the elevator.
   *
   * @return A command which moves the elevator to its highest point.
   */
  public Command top() {
    return moveTo(MAX_HEIGHT.in(Meters));
  }

  /**
   * Moves to the bottom of the elevator.
   *
   * @return A command which moves the elevator to its lowest point.
   */
  public Command bottom() {
    return moveTo(MIN_HEIGHT.in(Meters));
  }

  /**
   * Moves the elevator to a certain point.
   *
   * @param goalPosition The height for the elevator to be moved to, in meters.
   * @return A command to move the elevator to a certain height.
   */
  public Command moveTo(double goalPosition) {
    return run(() -> nextVoltage(goalPosition));
  }

  /**
   * Finds the next voltage to move the Elevator, and then moves the elevator towards that position.
   *
   * @param goalPosition the next position to move the elevator to, in radians.
   */
  private void nextVoltage(double goalPosition) {
    setpoint = goalPosition;
    double goal =
        !Double.isNaN(goalPosition)
            ? MathUtil.clamp(goalPosition, MIN_HEIGHT.in(Meters), MAX_HEIGHT.in(Meters))
            : MIN_HEIGHT.in(Meters);
    TrapezoidProfile.State prevSet = pid.getSetpoint();
    double fb = pid.calculate(hardware.getPosition(), goal);
    double accel = (pid.getSetpoint().velocity - prevSet.velocity) / PERIOD.in(Seconds);
    double feedforward = ff.calculate(hardware.getVelocity(), accel);
    hardware.setVoltage(feedforward + fb);
  }

  public Test goToTest(Measure<Distance> goal) {
    Command testCommand =
        moveTo(goal.in(Meters)).until(() -> atPosition(goal.in(Meters))).withTimeout(2);
    EqualityAssertion atGoal =
        Assertion.eAssert("Elevator Test Height (Meters)", () -> goal.in(Meters), () -> position());
    return new Test(testCommand, Set.of(atGoal));
  }

  /**
   * Determines whether the position of the elevator is close enough to a certain point.
   *
   * @param goal The certain position, in meters.
   * @return Whether the position of the elevator is within the position tolerance.
   */
  public boolean atPosition(double position) {
    return Math.abs(hardware.getPosition() - position) < POSITION_TOLERANCE.in(Meters);
  }

  @Override
  public void close() throws Exception {
    hardware.close();
  }

  @Log.NT
  public double position() {
    return hardware.getPosition();
  }

  @Log.NT
  public double velocity() {
    return hardware.getVelocity();
  }

  @Log.NT
  public double setpoint() {
    return setpoint;
  }
}
