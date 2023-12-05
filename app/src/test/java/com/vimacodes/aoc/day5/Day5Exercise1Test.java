package com.vimacodes.aoc.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Day5Exercise1Test {
  private final Day5Exercise1 exercise = new Day5Exercise1();

  @ParameterizedTest
  @ValueSource(
      strings =
          """
          seeds: 79 14 55 13

          seed-to-soil map:
          50 98 2
          52 50 48

          soil-to-fertilizer map:
          0 15 37
          37 52 2
          39 0 15

          fertilizer-to-water map:
          49 53 8
          0 11 42
          42 0 7
          57 7 4

          water-to-light map:
          88 18 7
          18 25 70

          light-to-temperature map:
          45 77 23
          81 45 19
          68 64 13

          temperature-to-humidity map:
          0 69 1
          1 0 69

          humidity-to-location map:
          60 56 37
          56 93 4
          """)
  void sampleInput(final String input) {
    long result = exercise.solve(input);
    Assertions.assertEquals(35, result);
  }

  @ParameterizedTest
  @ValueSource(strings = """
          """)
  void testInput(final String input) {
    long result = exercise.solve(input);
    Assertions.assertEquals(0, result);
  }
}
