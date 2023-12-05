package com.vimacodes.aoc.day6;

class Day6Exercise1 {
  public long solve(String text) {
    return text.lines().mapToLong(s -> 0).min().orElse(0);
  }
}
