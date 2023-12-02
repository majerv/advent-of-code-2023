package com.vimacodes.aoc.day2;

class Day2Exercise2 {

  public int solve(final String text, final Cubes config) {
    return text.lines()
        .peek(System.out::println)
        .map(Game::parse)
        .peek(System.out::println)
        .filter(g -> g.isValidConfig(config))
        .mapToInt(Game::getId)
        .sum();
  }
}
