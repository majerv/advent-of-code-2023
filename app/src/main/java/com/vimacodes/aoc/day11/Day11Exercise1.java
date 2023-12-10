package com.vimacodes.aoc.day11;

class Day11Exercise1 {

  public long solve(final String text) {
    return text.lines()
        //        .map(Oasis::parse)
        .peek(System.out::println)
        //        .flatMap(o -> o.histories().stream())
        .mapToInt(s -> 0)
        .sum();
  }
}
