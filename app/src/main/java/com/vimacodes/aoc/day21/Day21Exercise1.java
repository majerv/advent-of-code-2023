package com.vimacodes.aoc.day21;

class Day21Exercise1 {
  public long solve(final String text, final int steps) {
    Garden garden = Garden.parse(text);
    //    System.out.println(garden.prettyPrint());

    long result = garden.step(steps);
    System.out.println(result);

    return result;
  }
}
