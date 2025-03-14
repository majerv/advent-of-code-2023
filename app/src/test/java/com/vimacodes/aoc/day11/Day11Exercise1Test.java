package com.vimacodes.aoc.day11;

import com.vimacodes.aoc.Inputs;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day11Exercise1Test {
  private final Day11Exercise1 exercise = new Day11Exercise1();

  @Disabled
  @Test
  void testExpansion() {
    String input = Inputs.resourceToString("day11_sample");
    String expectedOutput = Inputs.resourceToString("day11_sample_expanded");

    String result = Universe.parse(input).expand().prettyPrintWithNumbers();
    Assertions.assertEquals(expectedOutput, result);
  }

  @ParameterizedTest
  @MethodSource
  void testInputs(final String inputName, long expectedResult) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(expectedResult, result);
  }

  private static Stream<Arguments> testInputs() {
    return Stream.of(Arguments.of("day11_sample", 374), Arguments.of("day11_input", 9957702));
  }
}
