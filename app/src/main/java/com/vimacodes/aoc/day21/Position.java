package com.vimacodes.aoc.day21;

import lombok.Value;

@Value
class Position {
  int row;
  int col;

  public Position up() {
    return new Position(row - 1, col);
  }

  public Position down() {
    return new Position(row + 1, col);
  }

  public Position left() {
    return new Position(row, col - 1);
  }

  public Position right() {
    return new Position(row, col + 1);
  }

  public boolean isValid(int rows, int cols) {
    return 0 <= row && row < rows && 0 <= col && col < cols;
  }
}
