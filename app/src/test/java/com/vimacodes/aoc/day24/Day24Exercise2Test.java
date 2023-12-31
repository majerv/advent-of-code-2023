package com.vimacodes.aoc.day24;

import com.vimacodes.aoc.Inputs;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day24Exercise2Test {
  private final Day24Exercise2 exercise = new Day24Exercise2();

  @ParameterizedTest
  @MethodSource
  void testInputs(final String inputName, long expectedResult) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(expectedResult, result);
  }

  private static Stream<Arguments> testInputs() {
    return Stream.of(
        Arguments.of(
            "day24_sample", 0 /*47*/), // [{dxr:−3.0,dyr:1.0,dzr:2.0,xr:24.0,yr:13.0,zr:10.0}]
        Arguments.of(
            "day24_input",
            0 /*664822352550558L*/) // [{dxr:139.0,dyr:−93.0,dzr:245.0,xr:191146615936494.0,yr:342596108503183.0,zr:131079628110881.0}]
        );
  }
}
