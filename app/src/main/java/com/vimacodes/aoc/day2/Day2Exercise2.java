package com.vimacodes.aoc.day2;

class Day2Exercise2 {

  public long solve(final String text) {
    return text.lines()
        .peek(System.out::println)
        .map(Game::parse)
        .map(Game::minimalConfig)
        .peek(c -> System.out.println("minimal: " + c))
        .mapToLong(Cubes::power)
        .peek(p -> System.out.println("power: " + p))
        .sum();
  }
}
