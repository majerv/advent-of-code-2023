package com.vimacodes.aoc.day9;

import com.vimacodes.aoc.Inputs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Day9Exercise2Test {
  private final Day9Exercise2 exercise = new Day9Exercise2();

  @ParameterizedTest
  @ValueSource(strings = "day9_sample")
  void sampleInput(final String inputName) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(2, result);
  }

  @ParameterizedTest
  @ValueSource(strings = "day9_input")
  void testInput(final String inputName) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(1100, result);
  }
}
