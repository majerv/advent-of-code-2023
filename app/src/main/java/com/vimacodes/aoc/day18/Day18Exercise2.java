package com.vimacodes.aoc.day18;

class Day18Exercise2 {
  public long solve(final String text) {
    DigPlan digPlan = DigPlan.parse2(text);
    System.out.println(digPlan);
    return digPlan.getTotalLavaP();
  }
}
