package com.vimacodes.aoc.day2;


class Day2Exercise1 {

  public int solve(final String text) {
    return text.lines()
        .peek(System.out::println)
        .mapToInt(this::gameIdIfPossibleOrZero)
        .peek(v -> System.out.println("game ID possible: " + v))
        .sum();
  }

  private int gameIdIfPossibleOrZero(final String line) {
    return 0;
  }
}
