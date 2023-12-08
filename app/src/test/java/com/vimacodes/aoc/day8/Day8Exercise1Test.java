package com.vimacodes.aoc.day8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Day8Exercise1Test {
  private final Day8Exercise1 exercise = new Day8Exercise1();

  @ParameterizedTest
  @ValueSource(
      strings =
          """
          """)
  void sampleInput(final String input) {
    long result = exercise.solve(input);
    Assertions.assertEquals(0, result);
  }

  @ParameterizedTest
  @ValueSource(
      strings =
          """
          """)
  void testInput(final String input) {
    long result = exercise.solve(input);
    Assertions.assertEquals(0, result);
  }
}
