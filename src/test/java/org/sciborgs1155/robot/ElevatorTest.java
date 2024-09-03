package org.sciborgs1155.robot;

import static org.sciborgs1155.lib.Test.runUnitTest;
import static org.sciborgs1155.lib.UnitTestingUtil.reset;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sciborgs1155.robot.elevator.Elevator;
import org.sciborgs1155.robot.elevator.ElevatorConstants;

public class ElevatorTest {
  private Elevator elevator;

  @BeforeEach
  public void setup() {
    elevator = Elevator.create();
  }

  @AfterEach
  public void destroy() throws Exception {
    reset(elevator);
  }

  @Test
  public void init() throws Exception {
    Elevator.create().close();
  }

  @Test
  public void topTest() {
    runUnitTest(elevator.goToTest(ElevatorConstants.MAX_HEIGHT));
  }

  @Test
  public void bottomTest() {
    runUnitTest(elevator.goToTest(ElevatorConstants.MIN_HEIGHT));
  }
}
