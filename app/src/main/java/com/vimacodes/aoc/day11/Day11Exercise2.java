package com.vimacodes.aoc.day11;

class Day11Exercise2 {

  public long solve(final String text) {
    Universe universe = Universe.parse(text);
    System.out.println(universe.prettyPrintWithNumbers());

    ExpandedUniverse expanded = universe.expand(1_000_000);
    return expanded.apsp().values().stream().mapToLong(i -> i).sum();
  }
}
