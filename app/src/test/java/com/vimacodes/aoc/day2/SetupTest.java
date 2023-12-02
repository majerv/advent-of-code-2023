package com.vimacodes.aoc.day2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SetupTest {

  @ParameterizedTest
  @CsvSource(
      ignoreLeadingAndTrailingWhitespace = false,
      value = {" 3 blue,3,BLUE"})
  void parseCountAndColor(String line, int expectedCount, Color expectedColor) {
    Setup setup = Setup.parse(line);
    Assertions.assertEquals(expectedCount, setup.count());
    Assertions.assertEquals(expectedColor, setup.color());
  }
}
