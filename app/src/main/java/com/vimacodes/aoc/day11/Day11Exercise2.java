package com.vimacodes.aoc.day11;

class Day11Exercise2 {

  public long solve(final String text) {
    Universe universe = Universe.parse(text);
    System.out.println(universe.prettyPrintWithNumbers());

    Universe expanded = universe.expand(10);
    //    System.out.println(expanded.prettyPrint());
    //    System.out.println(expanded.prettyPrintWithNumbers());

    return expanded.apsp().values().stream().mapToInt(i -> i).sum();
  }
}
