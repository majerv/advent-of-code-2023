package com.vimacodes.aoc.day12;

class Day12Exercise1 {

  public long solve(final String text) {
    return text.lines()
        .map(ConditionRecords::parse)
        .peek(System.out::println)
        .mapToLong(ConditionRecords::arrangements)
        .peek(System.out::println)
        .sum();
  }
}
