package com.vimacodes.aoc.day9;

class Day9Exercise1 {

  public long solve(final String text) {
    return text.lines().map(Oasis::parse).peek(System.out::println).mapToInt(s -> 0).sum();
  }
}
