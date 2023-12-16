package com.vimacodes.aoc.day16;

class Day16Exercise2 {
  public long solve(final String text) {
    Contraption contraption = Contraption.parse(text);
    contraption.prettyPrint();

    contraption.energize();
    contraption.prettyPrint();

    return contraption.energizedTileCount();
  }
}
