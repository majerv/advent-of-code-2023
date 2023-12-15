package com.vimacodes.aoc.day15;

class Day15Exercise2 {
  public long solve(final String text) {
    HASHMAP hashmap = HASHMAP.parse(text);
    return hashmap.totalFocusingPower();
  }
}
