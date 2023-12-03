package com.vimacodes.aoc.day3;

class Day3Exercise2 {

  public long solve(final String text) {
    Engine engine = Engine.parse(text);

    return engine.gears().stream().mapToLong(Gear::getRatio).sum();
  }
}
