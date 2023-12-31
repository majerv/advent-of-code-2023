package com.vimacodes.aoc.day24;

import com.vimacodes.aoc.Inputs;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day24Exercise1Test {
  private final Day24Exercise1 exercise = new Day24Exercise1();

  @ParameterizedTest
  @MethodSource
  void testInputs(final String inputName, Area area, long expectedResult) {
    long result = exercise.solve(Inputs.resourceToString(inputName), area);
    Assertions.assertEquals(expectedResult, result);
  }

  private static Stream<Arguments> testInputs() {
    return Stream.of(
        Arguments.of("day24_sample", new Area(new Point2d(7, 7), new Point2d(27, 27)), 2) //
        ,
        Arguments.of(
            "day24_input",
            new Area(
                new Point2d(200000000000000L, 200000000000000L),
                new Point2d(400000000000000L, 400000000000000L)),
            24192) //
        );
  }
}
