package com.vimacodes.aoc.day23;

class Day23Exercise1 {
  public long solve(final String text) {
    HikingMap hikingMap = HikingMap.parse(text);
    System.out.println(hikingMap.prettyPrint());

    return hikingMap.longestHike();
  }
}
