package com.vimacodes.aoc.day16;

class Day16Exercise2 {
  public long solve(final String text) {
    Contraption contraption = Contraption.parse(text);
    contraption.prettyPrint();

    long max = 0;
    for (int i = 0; i < contraption.getRows(); i++) {
      for (int j = 0; j < contraption.getCols(); j++) {
        if (i == 0) {
          contraption.energize(new Instruction(i, j, Direction.DOWN));
          max = Math.max(max, contraption.energizedTileCount());
        }

        if (i == contraption.getRows() - 1) {
          contraption.energize(new Instruction(i, j, Direction.UP));
          max = Math.max(max, contraption.energizedTileCount());
        }

        if (j == 0) {
          contraption.energize(new Instruction(i, j, Direction.RIGHT));
          max = Math.max(max, contraption.energizedTileCount());
        }

        if (j == contraption.getCols() - 1) {
          contraption.energize(new Instruction(i, j, Direction.LEFT));
          max = Math.max(max, contraption.energizedTileCount());
        }
      }
    }

    return max;
  }
}
