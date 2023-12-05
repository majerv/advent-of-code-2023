package com.vimacodes.aoc.day6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Day6Exercise1Test {
  private final Day6Exercise1 exercise = new Day6Exercise1();

  @ParameterizedTest
  @ValueSource(strings = """
          """)
  void sampleInput(final String input) {
    long result = exercise.solve(input);
    Assertions.assertEquals(0, result);
  }

  @ParameterizedTest
  @ValueSource(strings = """
          """)
  void testInput(final String input) {
    long result = exercise.solve(input);
    Assertions.assertEquals(0, result);
  }
}
