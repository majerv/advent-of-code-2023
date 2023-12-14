package com.vimacodes.aoc.day14;

class Day14Exercise1 {
  public long solve(final String text) {
    Platform platform = Platform.parse(text);
    System.out.println(platform.prettyPrint());

    Platform tilted = platform.tiltNorth();
    System.out.println(tilted.prettyPrint());

    return tilted.load();
  }
}
