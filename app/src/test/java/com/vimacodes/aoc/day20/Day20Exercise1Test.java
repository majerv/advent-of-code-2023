package com.vimacodes.aoc.day20;

import com.vimacodes.aoc.Inputs;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day20Exercise1Test {
  private final Day20Exercise1 exercise = new Day20Exercise1();

  @ParameterizedTest
  @MethodSource
  void testInputs(final String inputName, long expectedResult) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(expectedResult, result);
  }

  private static Stream<Arguments> testInputs() {
    return Stream.of(
        Arguments.of("day20_sample", 32000000L), //
        Arguments.of("day20_sample2", 11687500L), //
        Arguments.of("day20_input", 841763884L) //
        );
  }
}
