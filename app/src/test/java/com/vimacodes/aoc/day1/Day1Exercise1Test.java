package com.vimacodes.aoc.day1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Day1Exercise1Test {

  private final Day1Exercise1 exercise = new Day1Exercise1();

  @ParameterizedTest
  @CsvSource(
      strings =
          """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
            """)
  void sampleInput(final String input) {
    long count = input.lines().peek(System.out::println).count();
    System.out.println(count);

    int result = exercise.solve(input);
    Assertions.assertEquals(142, result);
  }
}
