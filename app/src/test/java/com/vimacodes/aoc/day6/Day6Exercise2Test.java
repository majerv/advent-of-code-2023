package com.vimacodes.aoc.day6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Day6Exercise2Test {
  private final Day6Exercise2 exercise = new Day6Exercise2();

  @ParameterizedTest
  @ValueSource(strings = """
          Time:      71530
          Distance:  940200
          """)
  void sampleInput(final String input) {
    long result = exercise.solve(input);
    Assertions.assertEquals(71503, result);
  }

  @ParameterizedTest
  @ValueSource(
      strings =
          """
          Time:        57726992
          Distance:   291117211762026
          """)
  void testInput(final String input) {
    long result = exercise.solve(input);
    Assertions.assertEquals(46561107, result);
  }
}
