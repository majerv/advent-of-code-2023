package com.vimacodes.aoc.day14;

class Day14Exercise2 {
  public long solve(final String text) {
    Platform platform = Platform.parse(text);
    System.out.println(platform.prettyPrint());
    return platform.loadAfterCycles(1_000_000_000);
  }
}
