package com.vimacodes.aoc.day16;

import lombok.Value;

@Value
class Instruction {
  int row;
  int col;
  Direction direction;

  Instruction up() {
    return new Instruction(row - 1, col, Direction.UP);
  }

  public Instruction down() {
    return new Instruction(row + 1, col, Direction.DOWN);
  }

  public Instruction left() {
    return new Instruction(row, col - 1, Direction.LEFT);
  }

  public Instruction right() {
    return new Instruction(row, col + 1, Direction.RIGHT);
  }

  public boolean isValid(int rows, int cols) {
    return 0 <= row && row < rows && 0 <= col && col < cols;
  }
}
