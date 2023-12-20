package com.vimacodes.aoc.day22;

class Day22Exercise1 {
  public long solve(final String text) {
    Wall wall = Wall.parse(text);
    //    System.out.println(wall);

    wall.freeFall();

    return wall.safeToRemove().size();
  }
}
