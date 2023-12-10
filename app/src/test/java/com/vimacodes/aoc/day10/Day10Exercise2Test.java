package com.vimacodes.aoc.day10;

import com.vimacodes.aoc.Inputs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Day10Exercise2Test {
  private final Day10Exercise2 exercise = new Day10Exercise2();

  @ParameterizedTest
  @ValueSource(strings = "day10_part2_sample")
  void sampleInput(final String inputName) {
    long result = exercise.solve(Inputs.resourceToString(inputName), 'F');
    Assertions.assertEquals(4, result);
  }

  @ParameterizedTest
  @ValueSource(strings = "day10_part2_sample2")
  void sampleInput2(final String inputName) {
    long result = exercise.solve(Inputs.resourceToString(inputName), 'F');
    Assertions.assertEquals(4, result);
  }

  @ParameterizedTest
  @ValueSource(strings = "day10_part2_sample3")
  void sampleInput3(final String inputName) {
    long result = exercise.solve(Inputs.resourceToString(inputName), 'F');
    Assertions.assertEquals(8, result);
  }

  @ParameterizedTest
  @ValueSource(strings = "day10_part2_sample4")
  void sampleInput4(final String inputName) {
    long result = exercise.solve(Inputs.resourceToString(inputName), '7');
    Assertions.assertEquals(10, result);
  }

  @ParameterizedTest
  @ValueSource(strings = "day10_input")
  void testInput(final String inputName) {
    long result = exercise.solve(Inputs.resourceToString(inputName), '7');
    Assertions.assertEquals(443, result);
  }
}
