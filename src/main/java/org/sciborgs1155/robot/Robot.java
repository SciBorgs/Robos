package org.sciborgs1155.robot;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.MetersPerSecondPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.Second;
import static edu.wpi.first.wpilibj2.command.button.RobotModeTriggers.*;

import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ProxyCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import monologue.Annotations.Log;
import monologue.Logged;
import monologue.Monologue;
import org.littletonrobotics.urcl.URCL;
import org.sciborgs1155.lib.CommandRobot;
import org.sciborgs1155.lib.FaultLogger;
import org.sciborgs1155.lib.InputStream;
import org.sciborgs1155.robot.Ports.OI;
import org.sciborgs1155.robot.commands.Autos;
import org.sciborgs1155.robot.drive.Drive;
import org.sciborgs1155.robot.drive.DriveConstants;
import org.sciborgs1155.robot.elevator.Elevator;
import org.sciborgs1155.robot.hanger.Hanger;
import org.sciborgs1155.robot.intake.Intake;
import org.sciborgs1155.robot.wrist.Wrist;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class Robot extends CommandRobot implements Logged {

  // INPUT DEVICES
  private final CommandXboxController operator = new CommandXboxController(OI.OPERATOR);
  private final CommandXboxController driver = new CommandXboxController(OI.DRIVER);

  // SUBSYSTEMS
  private final Drive drive = Drive.create();

  private final Elevator elevator =
      switch (Constants.ROBOT_TYPE) {
        case CHASSIS -> Elevator.none();
        default -> Elevator.create();
      };

  private final Wrist wrist =
      switch (Constants.ROBOT_TYPE) {
        case COMPLETE -> Wrist.create();
        default -> Wrist.none();
      };

  private final Intake intake =
      switch (Constants.ROBOT_TYPE) {
        case COMPLETE -> Intake.create();
        default -> Intake.none();
      };

  private final Hanger hanger =
      switch (Constants.ROBOT_TYPE) {
        case CHASSIS -> Hanger.none();
        default -> Hanger.create();
      };

  // COMMANDS
  @Log.NT private final Autos autos = new Autos();

  @Log.NT private double speedMultiplier = Constants.FULL_SPEED;

  /** The robot contains subsystems, OI devices, and commands. */
  public Robot() {
    configureGameBehavior();
    configureSubsystemDefaults();
    configureBindings();
  }

  /** Configures basic behavior during different parts of the game. */
  private void configureGameBehavior() {
    // Configure logging with DataLogManager, Monologue, FailureManagement, and URCL
    DataLogManager.start();
    Monologue.setupMonologue(this, "/Robot", false, true);
    addPeriodic(Monologue::updateAll, kDefaultPeriod);
    addPeriodic(FaultLogger::update, 1);

    if (isReal()) {
      URCL.start();
    } else {
      DriverStation.silenceJoystickConnectionWarning(true);
    }
  }

  /** Creates an input stream for a joystick. */
  private InputStream createJoystickStream(InputStream input, double maxSpeed, double maxRate) {
    return input
        .deadband(Constants.DEADBAND, 1)
        .negate()
        .scale(maxSpeed)
        .scale(() -> speedMultiplier)
        .signedPow(2)
        .rateLimit(maxRate);
  }

  /**
   * Configures subsystem default commands. Default commands are scheduled when no other command is
   * running on a subsystem.
   */
  private void configureSubsystemDefaults() {
    drive.setDefaultCommand(
        drive.drive(
            createJoystickStream(
                driver::getLeftX,
                DriveConstants.MAX_SPEED.in(MetersPerSecond),
                DriveConstants.MAX_ACCEL.in(MetersPerSecondPerSecond)),
            createJoystickStream(
                driver::getLeftY,
                DriveConstants.MAX_SPEED.in(MetersPerSecond),
                DriveConstants.MAX_ACCEL.in(MetersPerSecondPerSecond)),
            createJoystickStream(
                driver::getRightX,
                DriveConstants.MAX_ANGULAR_SPEED.in(RadiansPerSecond),
                DriveConstants.MAX_ANGULAR_ACCEL.in(RadiansPerSecond.per(Second)))));

    wrist.setDefaultCommand(wrist.retract());
    elevator.setDefaultCommand(elevator.bottom());
  }

  /** Configures trigger -> command bindings */
  private void configureBindings() {
    autonomous().whileTrue(new ProxyCommand(autos::get));

    driver
        .leftBumper()
        .or(driver.rightBumper())
        .onTrue(Commands.runOnce(() -> speedMultiplier = Constants.FULL_SPEED))
        .onFalse(Commands.run(() -> speedMultiplier = Constants.SLOW_SPEED));

    operator.a().whileTrue(wrist.extend());
  }
}
