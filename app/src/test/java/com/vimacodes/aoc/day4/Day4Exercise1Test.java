package com.vimacodes.aoc.day4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Day4Exercise1Test {
  private final Day4Exercise1 exercise = new Day4Exercise1();

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
