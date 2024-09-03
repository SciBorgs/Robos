package org.sciborgs1155.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public sealed interface Assertion {
  public void apply(boolean unitTest);

  public static record TruthAssertion(
      BooleanSupplier condition, String faultName, String description) implements Assertion {
    @Override
    public void apply(boolean unitTest) {
      if (unitTest) {
        assertTrue(condition, faultName + ": " + description);
      }
    }
  }

  public static record EqualityAssertion(
      String faultName, DoubleSupplier expected, DoubleSupplier actual, double delta)
      implements Assertion {
    @Override
    public void apply(boolean unitTest) {
      if (unitTest) {
        assertEquals(expected.getAsDouble(), actual.getAsDouble(), delta, faultName);
      }
    }
  }

  /**
   * @return a truth assertion
   */
  public static TruthAssertion tAssert(
      BooleanSupplier condition, String faultName, String description) {
    return new TruthAssertion(condition, faultName, description);
  }

  /**
   * @return an equality assertion
   */
  public static EqualityAssertion eAssert(
      String faultName, DoubleSupplier expected, DoubleSupplier actual, double delta) {
    return new EqualityAssertion(faultName, expected, actual, delta);
  }

  /**
   * @return an equality assertion
   */
  public static EqualityAssertion eAssert(
      String faultName, DoubleSupplier expected, DoubleSupplier actual) {
    return eAssert(faultName, expected, actual, 0);
  }
}
