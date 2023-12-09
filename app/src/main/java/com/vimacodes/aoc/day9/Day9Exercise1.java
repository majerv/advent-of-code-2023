package com.vimacodes.aoc.day9;

class Day9Exercise1 {

  public long solve(final String text) {
    return text.lines()
        .map(Oasis::parse)
        .peek(System.out::println)
        .flatMap(o -> o.histories().stream())
        .mapToInt(ValueHistory::extrapolate)
        .sum();
  }
}
