package com.vimacodes.aoc.day6;

class Day6Exercise1 {
  public long solve(String text) {

    return Races.parse(text).stream()
        .peek(System.out::println)
        .mapToLong(Race::numberOfWaysToBeatRecord)
        .reduce(1, (a, b) -> a * b);
  }
}
