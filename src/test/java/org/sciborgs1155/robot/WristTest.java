package org.sciborgs1155.robot;

import static org.sciborgs1155.lib.Test.runUnitTest;
import static org.sciborgs1155.lib.UnitTestingUtil.reset;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sciborgs1155.robot.wrist.Wrist;
import org.sciborgs1155.robot.wrist.WristConstants;

public class WristTest {
  private Wrist wrist;

  @BeforeEach
  public void setup() {
    wrist = Wrist.create();
  }

  @AfterEach
  public void destroy() throws Exception {
    reset(wrist);
  }

  @Test
  public void init() throws Exception {
    Wrist.create().close();
  }

  @Test
  public void extendTest() {
    runUnitTest(wrist.goToTest(WristConstants.MAX_ANGLE));
  }

  @Test
  public void retractTest() {
    runUnitTest(wrist.goToTest(WristConstants.MIN_ANGLE));
  }
}
