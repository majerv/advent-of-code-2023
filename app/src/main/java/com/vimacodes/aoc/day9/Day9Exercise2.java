package com.vimacodes.aoc.day9;

class Day9Exercise2 {

  public long solve(final String text) {
    return text.lines()
        .map(Oasis::parse)
        .peek(System.out::println)
        .flatMap(o -> o.histories().stream())
        .mapToInt(ValueHistory::extrapolateFirst)
        .sum();
  }
}
