package com.vimacodes.aoc.day10;

class Day10Exercise1 {

  public long solve(final String text) {
    return text.lines()
        //        .map(Oasis::parse)
        .peek(System.out::println)
        //        .flatMap(o -> o.histories().stream())
        //        .mapToInt(ValueHistory::extrapolate)
        .mapToInt(s -> 0)
        .sum();
  }
}
