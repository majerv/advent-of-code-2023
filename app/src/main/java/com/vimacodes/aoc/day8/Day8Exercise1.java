package com.vimacodes.aoc.day8;


class Day8Exercise1 {
  public long solve(final String text) {
    return text.lines().peek(System.out::println).mapToInt(s -> 0).sum();
  }
}
