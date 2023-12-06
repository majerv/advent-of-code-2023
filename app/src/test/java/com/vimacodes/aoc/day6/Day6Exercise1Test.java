package com.vimacodes.aoc.day6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Day6Exercise1Test {
  private final Day6Exercise1 exercise = new Day6Exercise1();

  @ParameterizedTest
  @ValueSource(
      strings = """
          Time:      7  15   30
          Distance:  9  40  200
          """)
  void sampleInput(final String input) {
    long result = exercise.solve(input);
    Assertions.assertEquals(288, result);
  }

  @ParameterizedTest
  @ValueSource(
      strings =
          """
          Time:        57     72     69     92
          Distance:   291   1172   1176   2026
          """)
  void testInput(final String input) {
    long result = exercise.solve(input);
    Assertions.assertEquals(160816, result);
  }
}
