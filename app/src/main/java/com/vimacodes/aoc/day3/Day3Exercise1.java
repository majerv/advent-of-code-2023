package com.vimacodes.aoc.day3;

class Day3Exercise1 {

  public long solve(final String text) {
    Engine engine = Engine.parse(text);

    return engine.numbers().stream()
        .filter(engine::isAdjacentToSymbol)
        .mapToInt(EngineNumber::getNumber)
        .sum();
  }
}
