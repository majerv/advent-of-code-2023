package com.vimacodes.aoc.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Day5Exercise1Test {
  private final Day5Exercise1 exercise = new Day5Exercise1();

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
