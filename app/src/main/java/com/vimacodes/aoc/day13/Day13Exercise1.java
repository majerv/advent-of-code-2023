package com.vimacodes.aoc.day13;

import java.util.Arrays;

class Day13Exercise1 {
  public long solve(final String text) {
    String[] parts = text.split("\\r\\n\\r\\n");

    return Arrays.stream(parts)
        .map(ReflectionNotes::parse)
        .peek(System.out::println)
        .mapToLong(ReflectionNotes::summarize)
        .sum();
  }
}
