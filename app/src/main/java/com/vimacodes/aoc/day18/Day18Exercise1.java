package com.vimacodes.aoc.day18;

class Day18Exercise1 {
  public long solve(final String text) {
    DigPlan digPlan = DigPlan.parse(text);
    //    System.out.println(digPlan);

    return digPlan.getTotalLavaP();
  }
}
