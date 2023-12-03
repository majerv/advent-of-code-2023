package com.vimacodes.aoc.day3;

class Day3Exercise1 {

  public long solve(final String text) {
    Engine engine = Engine.parse(text);
    //    System.out.println(engine);

    return engine.numbers().stream()
        .filter(engine::isAdjacentToSymbol)
        .mapToLong(EngineNumber::getNumber)
        .sum();
  }
}
