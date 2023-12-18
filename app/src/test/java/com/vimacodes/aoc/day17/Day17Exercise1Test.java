package com.vimacodes.aoc.day17;

import com.vimacodes.aoc.Inputs;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day17Exercise1Test {
  private final Day17Exercise1 exercise = new Day17Exercise1();

  @ParameterizedTest
  @MethodSource
  void testInputs(final String inputName, long expectedResult) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(expectedResult, result);
  }

  private static Stream<Arguments> testInputs() {
    return Stream.of(
        Arguments.of("day17_sample2", 5), //
        Arguments.of("day17_sample3", 11), //
        Arguments.of("day17_sample", 102), //
        Arguments.of("day17_input", 956) //
        );
  }
}
