package com.vimacodes.aoc.day17;

import com.vimacodes.aoc.Inputs;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day17Exercise2Test {
  private final Day17Exercise2 exercise = new Day17Exercise2();

  @ParameterizedTest
  @MethodSource
  void testInputs(final String inputName, long expectedResult) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(expectedResult, result);
  }

  private static Stream<Arguments> testInputs() {
    return Stream.of(
        Arguments.of("day17_sample", 94), //
        Arguments.of("day17_sample_ultra", 71), //
        Arguments.of("day17_input", 1106) //
        );
  }
}
