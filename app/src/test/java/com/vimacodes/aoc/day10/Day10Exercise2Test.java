package com.vimacodes.aoc.day10;

import com.vimacodes.aoc.Inputs;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day10Exercise2Test {
  private final Day10Exercise2 exercise = new Day10Exercise2();

  @ParameterizedTest
  @MethodSource
  void testInputs(final String inputName, final long expectedResult) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(expectedResult, result);
  }
  
  private static Stream<Arguments> testInputs() {
    return Stream.of(
        Arguments.of("day10_part2_sample", 4),
        Arguments.of("day10_part2_sample2", 4),
        Arguments.of("day10_part2_sample3", 8),
        Arguments.of("day10_part2_sample4", 10),
        Arguments.of("day10_input", 443));
  }
}
