package com.vimacodes.aoc.day3;


record Gear(int part1, int part2) {
  public long getRatio() {
    return (long) part1 * part2;
  }
}
