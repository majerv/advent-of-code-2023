package com.vimacodes.aoc.day14;

import java.util.Arrays;

class Day14Exercise1 {
  public long solve(final String text) {
    String[] parts = text.split("\\r\\n\\r\\n");

    return Arrays.stream(parts)
//        .map(ReflectionNotes::parse)
        .peek(System.out::println)
//        .mapToLong(ReflectionNotes::summarize)
        .mapToLong(i -> 0)
        .sum();
  }
}
