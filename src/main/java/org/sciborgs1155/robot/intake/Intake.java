package org.sciborgs1155.robot.intake;

import static edu.wpi.first.units.Units.Seconds;
import static org.sciborgs1155.robot.intake.IntakeConstants.*;

import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import monologue.Annotations.Log;
import monologue.Logged;
import org.sciborgs1155.robot.Robot;


/*
 * I don't see any implementation of the jaw in here,
 * and therefore there's no way to clamp or drop the note,
 * so I'd recommend adding that in addition to the rollers
 */
public class Intake extends SubsystemBase implements Logged, AutoCloseable {

  private IntakeIO hardware;

  /**
   * Constructor.
   *
   * @param intake IntakeIO for the hardware.
   */
  private Intake(IntakeIO intake) {
    this.hardware = intake;
  }

  /**
   * Creates a new Intake, will return a real intake if the robot is real and a none intake if the
   * robot isn't real.
   *
   * @return a new Intake.
   */
  public static Intake create() {
    return Robot.isReal() ? new Intake(new RealIntake()) : new Intake(new NoIntake());
  }

  /**
   * Creates a none intake class.
   *
   * @return a new NoIntake.
   */
  public static Intake none() {
    return new Intake(new NoIntake());
  }

  /**
   * Intakes until a cube is detected in the sensors.
   *
   * @return A command which intakes until there is a cube detected in the sensors.
   */
  public Command intake() {
    //should use the method you defined in class for hasCube
    return forward().until(hardware::hasCube).andThen(stop());
  }

  /**
   * Runs the intake for a bit.
   *
   * @param power A value between -1 and 1 representing the power of the Intake.
   * @return a command which runs the intake.
   */
  public Command runIntake(double power) {
    return runOnce(() -> hardware.setPower(power))
        .andThen(Commands.idle(this))
        .finallyDo(() -> hardware.setPower(0));
  }

  /**
   * Runs the intake forward.
   *
   * @return A command which runs the intake forwards.
   */
  public Command forward() {
    return runIntake(INTAKE_SPEED);
  }

  /**
   * Runs the intake backward.
   *
   * @return A command which runs the intake in reverse.
   */
  public Command reverse() {
    return runIntake(-INTAKE_SPEED);
  }

  /**
   * Stops the intake.
   *
   * @return A command which sets the intake power to 0.
   */
  public Command stop() {
    return runIntake(0);
  }

  /**
   * A Trigger for when there is a cube in the intake.
   *
   * @return A Trigger that activates when both sensors detect a cube.
   */
  public Trigger cube() {
    return new Trigger(hardware::hasCube)
        .debounce(DEBOUNCE_TIME.in(Seconds), DebounceType.kFalling);
  }

  @Override
  public void close() throws Exception {
    hardware.close();
  }

  @Log.NT
  public boolean hasCube() {
    return hardware.hasCube();
  }
}
