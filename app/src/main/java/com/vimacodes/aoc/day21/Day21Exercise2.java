package com.vimacodes.aoc.day21;

class Day21Exercise2 {
  public long solve(final String text, final int steps) {
    Garden garden = Garden.parse(text);
    return garden.stepOnInfiniteGrid(steps);
  }
}
