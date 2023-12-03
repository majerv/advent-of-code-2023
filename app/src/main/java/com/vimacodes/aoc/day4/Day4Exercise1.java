package com.vimacodes.aoc.day4;


class Day4Exercise1 {

  public long solve(final String text) {
    return text.lines()
            .mapToInt(s -> 0)
            .peek(v -> System.out.println("value: " + v))
            .sum();
  }
}
