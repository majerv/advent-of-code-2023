package com.vimacodes.aoc.day21;

import com.vimacodes.aoc.Inputs;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day21Exercise2Test {
  private final Day21Exercise2 exercise = new Day21Exercise2();

  @ParameterizedTest
  @MethodSource
  void testInputs(final String inputName, int steps, long expectedResult) {
    long result = exercise.solve(Inputs.resourceToString(inputName), steps);
    Assertions.assertEquals(expectedResult, result);
  }

  private static Stream<Arguments> testInputs() {
    return Stream.of(
        Arguments.of("day21_input", 26501365, 634549784009844L) //
        );
  }
}
