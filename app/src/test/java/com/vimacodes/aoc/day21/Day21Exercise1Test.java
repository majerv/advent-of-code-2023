package com.vimacodes.aoc.day21;

import com.vimacodes.aoc.Inputs;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day21Exercise1Test {
  private final Day21Exercise1 exercise = new Day21Exercise1();

  @ParameterizedTest
  @MethodSource
  void testInputs(final String inputName, int steps, long expectedResult) {
    long result = exercise.solve(Inputs.resourceToString(inputName), steps);
    Assertions.assertEquals(expectedResult, result);
  }

  private static Stream<Arguments> testInputs() {
    return Stream.of(
        Arguments.of("day21_sample", 6, 16L), //
        Arguments.of("day21_input", 64, 3816L) //
        );
  }
}
