package com.vimacodes.aoc.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import lombok.Value;

@Value
class Maze {

  List<List<Character>> maze;
  int rows;
  int cols;

  public static Maze parse(String text) {
    List<List<Character>> maze =
        text.lines().map(line -> line.chars().mapToObj(c -> (char) c).toList()).toList();

    return new Maze(maze, maze.size(), maze.get(0).size());
  }

  public Position findStart() {
    for (int i = 0; i < maze.size(); i++) {
      var line = maze.get(i);
      for (int j = 0; j < line.size(); j++) {
        var c = line.get(j);
        if (c == 'S') return new Position(i, j);
      }
    }
    return new Position(-1, -1);
  }

  public List<Position> findLoop(Position start) {
    System.out.println("Finding loop from start position: " + start);

    List<Position> loop = new ArrayList<>();

    Position prev = null;
    Position pos = start;
    do {
      System.out.printf("At position: [%s,%s]=%s\n", pos.getRow(), pos.getCol(), charAt(pos));
      loop.add(pos);
      var next = findNextPos(pos, prev);
      prev = pos;
      pos = next;
    } while (!pos.equals(start));

    return loop;
  }

  private Position findNextPos(Position current, Position prev) {
    List<Position> adjacent = findConnectingPositions(current);
    adjacent.remove(prev);
    return adjacent.get(0);
  }

  private List<Position> findConnectingPositions(Position current) {
    List<Position> adjacent = new ArrayList<>();

    ifConnects(current, current.up(), Maze::isUpLink).map(adjacent::add);
    ifConnects(current, current.down(), Maze::isDownLink).map(adjacent::add);
    ifConnects(current, current.left(), Maze::isLeftLink).map(adjacent::add);
    ifConnects(current, current.right(), Maze::isRightLink).map(adjacent::add);

    return adjacent;
  }

  private Optional<Position> ifConnects(
      Position current, Position other, BiFunction<Character, Character, Boolean> predicate) {
    Character c = charAt(current);
    Character c2 = charAt(other);

    if (c == null || c2 == null || !predicate.apply(c, c2)) return Optional.empty();
    return Optional.of(other);
  }

  private Character charAt(Position pos) {
    if (isValidPos(pos)) return maze.get(pos.getRow()).get(pos.getCol());
    return null;
  }

  private boolean isValidPos(Position pos) {
    int row = pos.getRow();
    int col = pos.getCol();

    return 0 <= row && row < rows && 0 <= col && col < cols;
  }

  public String toPrettyString() {
    String mazePretty =
        maze.stream()
            .map(row -> row.stream().map(Object::toString).collect(Collectors.joining("")))
            .collect(Collectors.joining("\n"));

    return "rows: %d, cols: %d\n%s\n".formatted(rows, cols, mazePretty);
  }

  private static boolean isUpLink(Character c, Character link) {
    return switch (c) {
      case 'S', '|', 'L', 'J' -> link == '|' || link == '7' || link == 'F' || link == 'S';
      default -> false;
    };
  }

  private static boolean isDownLink(Character c, Character link) {
    return switch (c) {
      case 'S', '|', '7', 'F' -> link == '|' || link == 'L' || link == 'J' || link == 'S';
      default -> false;
    };
  }

  private static boolean isLeftLink(Character c, Character link) {
    return switch (c) {
      case 'S', '-', 'J', '7' -> link == '-' || link == 'L' || link == 'F' || link == 'S';
      default -> false;
    };
  }

  private static boolean isRightLink(Character c, Character link) {
    return switch (c) {
      case 'S', '-', 'L', 'F' -> link == '-' || link == 'J' || link == '7' || link == 'S';
      default -> false;
    };
  }

  public List<Position> findEnclosedPositions() {
    List<Position> enclosed = new ArrayList<>();
    return enclosed;
  }
}
