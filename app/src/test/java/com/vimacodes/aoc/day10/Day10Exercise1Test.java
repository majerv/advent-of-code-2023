package com.vimacodes.aoc.day10;

import com.vimacodes.aoc.Inputs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Day10Exercise1Test {
  private final Day10Exercise1 exercise = new Day10Exercise1();

  @ParameterizedTest
  @ValueSource(strings = "day10_sample")
  void sampleInput(final String inputName) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(4, result);
  }

  @ParameterizedTest
  @ValueSource(strings = "day10_sample2")
  void sampleInput2(final String inputName) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(8, result);
  }

  @ParameterizedTest
  @ValueSource(strings = "day10_input")
  void testInput(final String inputName) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(6979, result);
  }
}
