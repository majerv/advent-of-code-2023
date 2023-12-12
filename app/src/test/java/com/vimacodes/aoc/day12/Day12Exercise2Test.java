package com.vimacodes.aoc.day12;

import com.vimacodes.aoc.Inputs;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day12Exercise2Test {
  private final Day12Exercise2 exercise = new Day12Exercise2();

  @ParameterizedTest
  @MethodSource
  void testInputs(final String inputName, long expectedResult) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(expectedResult, result);
  }

  private static Stream<Arguments> testInputs() {
    return Stream.of(
        Arguments.of("day12_sample", 525152), Arguments.of("day12_input", 7030194981795L));
  }
}
