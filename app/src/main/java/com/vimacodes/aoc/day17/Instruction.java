package com.vimacodes.aoc.day17;

import java.util.List;
import lombok.Value;

@Value
class Instruction {
  private static final int COUNTER_LIMIT = 10;

  private static final int ULTRA_COUNTER_MIN = 4;
  private static final int ULTRA_COUNTER_MAX = 10;

  int row;
  int col;
  Direction direction;
  int counter;

  Instruction up() {
    return new Instruction(row - 1, col, Direction.UP, calculateCounter(Direction.UP));
  }

  public Instruction down() {
    return new Instruction(row + 1, col, Direction.DOWN, calculateCounter(Direction.DOWN));
  }

  public Instruction left() {
    return new Instruction(row, col - 1, Direction.LEFT, calculateCounter(Direction.LEFT));
  }

  public Instruction right() {
    return new Instruction(row, col + 1, Direction.RIGHT, calculateCounter(Direction.RIGHT));
  }

  public Position getPosition() {
    return new Position(row, col);
  }

  public boolean isValid(int rows, int cols) {
    if (counter > COUNTER_LIMIT) return false;

    return 0 <= row && row < rows && 0 <= col && col < cols;
  }

  public List<Instruction> nextSteps() {
    if (direction == null) return List.of(up(), down(), left(), right());

    return switch (direction) {
      case UP -> List.of(up(), left(), right());
      case DOWN -> List.of(down(), left(), right());
      case LEFT -> List.of(up(), down(), left());
      case RIGHT -> List.of(up(), down(), right());
    };
  }

  public List<Instruction> nextStepsUltra() {
    if (direction == null) return List.of(up(), down(), left(), right());

    return switch (direction) {
      case UP -> shouldContinue() ? List.of(up()) : List.of(up(), left(), right());
      case DOWN -> shouldContinue() ? List.of(down()) : List.of(down(), left(), right());
      case LEFT -> shouldContinue() ? List.of(left()) : List.of(up(), down(), left());
      case RIGHT -> shouldContinue() ? List.of(right()) : List.of(up(), down(), right());
    };
  }

  private boolean shouldContinue() {
    return counter < ULTRA_COUNTER_MIN;
  }

  private int calculateCounter(Direction newDirection) {
    return direction == newDirection ? counter + 1 : 1;
  }
}
