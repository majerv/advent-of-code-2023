package com.vimacodes.aoc.day11;


class Day11Exercise1 {

  public long solve(final String text) {
    Universe universe = Universe.parse(text);
    System.out.printf("rows %d, cols: %d\n", universe.getRows(), universe.getCols());
    System.out.println(universe.prettyPrint());
    System.out.println(universe.prettyPrintWithNumbers());

    Universe expanded = universe.expand();
    System.out.println(expanded.prettyPrint());
    System.out.println(expanded.prettyPrintWithNumbers());

    return expanded.apsp().values().stream().mapToInt(i -> i).sum();
  }
}
