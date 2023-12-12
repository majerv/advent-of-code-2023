package com.vimacodes.aoc.day12;

class Day12Exercise2 {

  public long solve(final String text) {
    return text.lines()
        .map(ConditionRecords::parse)
        .map(ConditionRecords::unfold)
        .mapToLong(ConditionRecords::arrangements)
        .sum();
  }
}
