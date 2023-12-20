package com.vimacodes.aoc.day21;

import java.util.*;
import java.util.stream.Collectors;
import lombok.Value;

@Value
class Garden {

  char[][] plots;
  int rows;
  int cols;
  Position start;

  public static Garden parse(String text) {
    List<String> lines = text.lines().toList();

    int rows = lines.size();
    int cols = lines.get(0).length();

    char[][] plots = new char[rows][cols];
    Position start = new Position(0, 0);

    String line;
    for (int i = 0; i < rows; i++) {
      line = lines.get(i);
      for (int j = 0; j < cols; j++) {
        char c = line.charAt(j);
        plots[i][j] = c;
        if (c == 'S') {
          start = new Position(i, j);
        }
      }
    }

    return new Garden(plots, rows, cols, start);
  }

  public long step(int maxSteps) {
    return step(maxSteps, start);
  }

  public long step(int sr, int sc, int maxSteps) {
    return step(maxSteps, new Position(sr, sc));
  }

  public long step(int maxSteps, Position start) {
    Deque<Set<Position>> steps = new LinkedList<>();
    steps.add(Collections.singleton(start));

    for (int i = 0; i < maxSteps; i++) {
      //      System.out.println("Stepping: " + stepCount);

      Set<Position> currentPositions = steps.removeFirst();
      steps.add(nextValidPositions(currentPositions));
    }

    return steps.peek().size();
  }

  /**
   * Implemented first with "infinite space" - i.e. walking really on the grid... ran for so long...
   * Refactored based on a popular idea fueled by assumptions on the input.
   */
  public long stepOnInfiniteGrid(int steps) {
    int size = rows;

    int sr = start.getRow();
    int sc = start.getCol();

    int gridWidth = steps / size - 1;
    long numberOfGridsWithOddSteps = sqrt((gridWidth / 2 * 2 + 1));
    long numberOfGridsWithEvenSteps = sqrt((gridWidth + 1) / 2 * 2);

    long placesOnOriginalGridWithOddSteps = step(size * 4 + 1, start);
    long placesOnOriginalGridWithEvenSteps = step(size * 4, start);

    long placesOnGridDiamondEdges =
        step(size - 1, sc, size - 1)
            + step(sr, size - 1, size - 1)
            + step(0, sc, size - 1)
            + step(sr, 0, size - 1);

    int stepsOnLargeDiagonalPolygon = 3 * size / 2 - 1;
    long placesOnLargeDiagonalPolygon =
        step(size - 1, 0, stepsOnLargeDiagonalPolygon)
            + step(size - 1, size - 1, stepsOnLargeDiagonalPolygon)
            + step(0, 0, stepsOnLargeDiagonalPolygon)
            + step(0, size - 1, stepsOnLargeDiagonalPolygon);

    int stepsOnSmallDiagonalPolygon = size / 2 - 1;
    long placesOnSmallDiagonalPolygon =
        step(size - 1, 0, stepsOnSmallDiagonalPolygon)
            + step(size - 1, size - 1, stepsOnSmallDiagonalPolygon)
            + step(0, 0, stepsOnSmallDiagonalPolygon)
            + step(0, size - 1, stepsOnSmallDiagonalPolygon);

    return numberOfGridsWithOddSteps * placesOnOriginalGridWithOddSteps
        + numberOfGridsWithEvenSteps * placesOnOriginalGridWithEvenSteps
        + placesOnGridDiamondEdges
        + (gridWidth + 1) * placesOnSmallDiagonalPolygon
        + gridWidth * placesOnLargeDiagonalPolygon;
  }

  private long sqrt(long i) {
    return i * i;
  }

  private Set<Position> nextValidPositions(Set<Position> positions) {
    return positions.stream().flatMap(pos -> nextPos(pos).stream()).collect(Collectors.toSet());
  }

  private List<Position> nextPos(Position pos) {
    List<Position> next = new ArrayList<>();

    next.add(pos.up());
    next.add(pos.down());
    next.add(pos.left());
    next.add(pos.right());

    return next.stream().filter(this::isValid).toList();
  }

  private boolean isValid(Position pos) {
    return pos.isValid(rows, cols) && plots[pos.getRow()][pos.getCol()] != '#';
  }
}
