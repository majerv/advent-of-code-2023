package com.vimacodes.aoc.day11;

import com.vimacodes.aoc.Inputs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Day11Exercise1Test {
  private final Day11Exercise1 exercise = new Day11Exercise1();

  @ParameterizedTest
  @ValueSource(strings = "day11_sample")
  void sampleInput(final String inputName) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(0, result);
  }

  @ParameterizedTest
  @ValueSource(strings = "day11_input")
  void testInput(final String inputName) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(0, result);
  }
}
