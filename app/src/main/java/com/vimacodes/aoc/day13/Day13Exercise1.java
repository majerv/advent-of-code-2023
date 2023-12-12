package com.vimacodes.aoc.day13;

class Day13Exercise1 {
  public long solve(final String text) {
    return text.lines()
        //        .map(Class::parse)
        .peek(System.out::println)
        //        .mapToLong(Class::reduce)
        .peek(System.out::println)
        .mapToInt(s -> 0)
        .sum();
  }
}
