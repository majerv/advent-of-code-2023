package com.vimacodes.aoc.day22;

class Day22Exercise2 {
  public long solve(final String text) {
    Wall wall = Wall.parse(text);

    wall.freeFall();

    return wall.chainReactionSum();
  }
}
