package com.vimacodes.aoc.day15;

import java.util.Arrays;

class Day15Exercise1 {
  public long solve(final String text) {
    String[] parts = text.split("\\r\\n\\r\\n");

    return Arrays.stream(parts)
//        .map(ReflectionNotes::parse)
        .peek(System.out::println)
        .mapToLong(s -> 0)
        .sum();
  }
}
