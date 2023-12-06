package com.vimacodes.aoc.day7;

class Day7Exercise1 {
  public long solve(final String text) {
    return text.lines().peek(System.out::println).mapToLong(s -> 0).sum();
  }
}
