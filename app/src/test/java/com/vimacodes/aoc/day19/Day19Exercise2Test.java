package com.vimacodes.aoc.day19;

import com.vimacodes.aoc.Inputs;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day19Exercise2Test {
  private final Day19Exercise2 exercise = new Day19Exercise2();

  @ParameterizedTest
  @MethodSource
  void testInputs(final String inputName, long expectedResult) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(expectedResult, result);
  }

  private static Stream<Arguments> testInputs() {
    return Stream.of(
        Arguments.of("day19_sample", 167409079868000L), //
        Arguments.of("day19_input", 131899818301477L) //
        );
  }
}
