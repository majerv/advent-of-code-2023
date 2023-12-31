package com.vimacodes.aoc.day23;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nonnull;

class HikingMap {

  private final char[][] map;
  private final int rows;
  private final int cols;
  private final Position start;
  private final Position end;

  private final Map<Position, Set<DistanceTo>> crossings;

  public HikingMap(char[][] map, int rows, int cols, Position start, Position end) {
    this.map = map;
    this.rows = rows;
    this.cols = cols;
    this.start = start;
    this.end = end;

    crossings = new HashMap<>();

    // path between crossings is simple, yet it cause an overhead -> compact |-............-| paths
    Deque<Position> process = new LinkedList<>();
    process.add(start);

    Set<Position> processed = new HashSet<>();
    while (!process.isEmpty()) {
      Position pos = process.removeFirst();
      crossings.putIfAbsent(pos, new HashSet<>());
      Set<Position> neighbours = getNeighboursNoSlopes(pos);
      for (var neighbour : neighbours) {
        DistanceTo nextCrossing = findNextCrossingsFrom(neighbour, pos);
        Set<DistanceTo> distanceTos = crossings.get(pos);
        distanceTos.add(nextCrossing);
        if (!processed.contains(nextCrossing.to())) {
          process.add(nextCrossing.to());
        }
      }
      processed.add(pos);
    }
  }

  private DistanceTo findNextCrossingsFrom(Position pos, Position prev) {
    Set<Position> path = new HashSet<>();
    path.add(prev);
    path.add(pos);

    Position current = pos;
    int forward = 1;
    Set<Position> neighbours = getNeighboursNoSlopes(current);
    neighbours.removeAll(path);

    while (neighbours.size() == 1) {
      ++forward;
      current = neighbours.stream().findFirst().get();
      path.add(current);
      neighbours = getNeighboursNoSlopes(current);
      neighbours.removeAll(path);
    }

    return new DistanceTo(forward, current);
  }

  public static HikingMap parse(String text) {
    List<String> lines = text.lines().toList();

    int rows = lines.size();
    int cols = lines.get(0).length();

    char[][] map = new char[rows][cols];

    String line;
    for (int i = 0; i < rows; i++) {
      line = lines.get(i);
      for (int j = 0; j < cols; j++) {
        char c = line.charAt(j);
        map[i][j] = c;
      }
    }

    Position start = new Position(0, lines.get(0).indexOf("."));
    Position end = new Position(rows - 1, lines.get(rows - 1).indexOf("."));

    return new HikingMap(map, rows, cols, start, end);
  }

  public long longestHike() {
    return longestHikeFrom(start, new HashSet<>(), this::getNeighbours) - 1;
  }

  public long longestHikeNoSlopes() {
    return longestHikeFromCompacted(start, new HashSet<>());
  }

  private long longestHikeFromCompacted(Position position, Set<Position> currentPath) {
    if (position.equals(end)) {
      return 0;
    }

    currentPath.add(position);

    Set<DistanceTo> distanceTos =
        crossings.get(position).stream()
            .filter(dt -> !currentPath.contains(dt.to()))
            .collect(Collectors.toSet());

    long result =
        distanceTos.stream()
            .mapToLong(dt -> dt.distance() + longestHikeFromCompacted(dt.to(), currentPath))
            .max()
            .orElse(0);

    currentPath.remove(position);

    return result;
  }

  private long longestHikeFrom(
      Position position,
      Set<Position> currentPath,
      Function<Position, Set<Position>> neighbourProvider) {

    if (position.equals(end)) {
      return 0;
    }

    long forward = 1;
    currentPath.add(position);

    Set<Position> neighbours = neighbourProvider.apply(position);
    neighbours.removeAll(currentPath);

    while (neighbours.size() == 1) {
      ++forward;
      position = neighbours.stream().findFirst().get();
      currentPath.add(position);
      neighbours = neighbourProvider.apply(position);
      neighbours.removeAll(currentPath);
    }

    if (neighbours.isEmpty()) {
      if (position.equals(end)) {
        return forward;
      } else {
        return 0;
      }
    } else {
      //      System.out.printf("Branching at %s with steps: %d\n", position, forward);
      var result =
          forward
              + neighbours.stream()
                  .mapToLong(n -> longestHikeFrom(n, new HashSet<>(currentPath), neighbourProvider))
                  .max()
                  .orElse(0);
      //      System.out.printf("Longest from %s: %d\n", position, result);
      return result;
    }
  }

  @Nonnull
  private Set<Position> getNeighbours(Position position) {
    Set<Position> neighbours = new HashSet<>();
    char symbol = getSymbolAt(position);

    if (symbol == '>') neighbours.add(position.right());
    else if (symbol == '<') neighbours.add(position.left());
    else if (symbol == '^') neighbours.add(position.up());
    else if (symbol == 'v') neighbours.add(position.down());
    else
      neighbours.addAll(List.of(position.up(), position.down(), position.left(), position.right()));

    return neighbours.stream()
        .filter(p -> p.isValid(rows, cols))
        .filter(p -> getSymbolAt(p) != '#')
        .collect(Collectors.toSet());
  }

  @Nonnull
  private Set<Position> getNeighboursNoSlopes(Position position) {
    return Stream.of(position.up(), position.down(), position.left(), position.right())
        .filter(p -> p.isValid(rows, cols))
        .filter(p -> getSymbolAt(p) != '#')
        .collect(Collectors.toSet());
  }

  private char getSymbolAt(Position p) {
    return map[p.getRow()][p.getCol()];
  }

  public String prettyPrint() {
    StringBuilder str = new StringBuilder();

    str.append("Rows: ").append(rows).append(" cols: ").append(cols).append("\n");
    str.append("Start: ").append(start).append("\n");
    str.append("End: ").append(end).append("\n");

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        str.append(map[i][j]);
      }
      str.append("\n");
    }

    return str.toString();
  }
}
