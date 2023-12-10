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
    List<List<Character>> maze = text.lines().map(Maze::lineToChars).toList();

    return new Maze(maze, maze.size(), maze.get(0).size());
  }

  private static List<Character> lineToChars(String line) {
    List<Character> list = line.chars().mapToObj(c -> (char) c).toList();
    return new ArrayList<>(list);
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
      //      System.out.printf("At position: [%s,%s]=%s\n", pos.getRow(), pos.getCol(),
      // charAt(pos));
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

  public List<Position> findPositionsEnclosedBy(List<Position> loop, Character cheat) {
    List<Position> enclosed = new ArrayList<>();
    Integer[][] escapePlan = new Integer[rows][cols];

    loop.forEach(p -> escapePlan[p.getRow()][p.getCol()] = 10);

    printPlan(escapePlan, loop);

    Position start = findStart();
    maze.get(start.getRow()).set(start.getCol(), calculateRealStartPipeSign(cheat));

    Position current;
    for (int i = 0; i < maze.size(); i++) {
      var line = maze.get(i);
      for (int j = 0; j < line.size(); j++) {
        current = new Position(i, j);
        if (!canEscapeFrom(current, escapePlan, loop) && !loop.contains(current)) {
          enclosed.add(current);
        }
        //        printPlan(escapePlan, loop);
      }
    }
    printPlan(escapePlan, loop);

    return enclosed;
  }

  public int findPositionsEnclosedByWithRayCast(List<Position> loop, Character cheat) {
    int enclosed = 0;
    Character[][] clearMaze = new Character[rows][cols];

    Position start = findStart();
    maze.get(start.getRow()).set(start.getCol(), calculateRealStartPipeSign(cheat));

    Position current;
    Character c;
    for (int i = 0; i < maze.size(); i++) {
      var line = maze.get(i);
      for (int j = 0; j < line.size(); j++) {
        c = maze.get(i).get(j);
        current = new Position(i, j);
        boolean loopMember = loop.contains(current);
        clearMaze[i][j] = loopMember ? c : '.';
        if (!loopMember && countCrossings(clearMaze, i, j) % 2 == 1) {
          ++enclosed;
        }
      }
    }

    return enclosed;
  }

  private int countCrossings(Character[][] clearMaze, int from, int to) {
    int crossings = 0;
    for (int i = 0; i < to; i++) {
      Character c = clearMaze[from][i];
      if (c == 'J' || c == '|' || c == 'L') ++crossings;
    }
    ;
    return crossings;
  }

  private Character calculateRealStartPipeSign(Character cheat) {
    // tbd
    return cheat;
  }

  private void printPlan(Integer[][] escapePlan, List<Position> loop) {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < escapePlan.length; i++) {
      var row = escapePlan[i];
      for (int j = 0; j < row.length; j++) {
        Integer num = row[j];
        Position pos = new Position(i, j);
        Character c = charAt(pos);
        if (loop.contains(pos)) str.append(c);
        else if (num == null) str.append(".");
        else if (num == -1) str.append("I");
        else if (num == 1) str.append("O");
        //        else if (c != '.') str.append(".");
        else str.append("?");
      }
      str.append("\n");
    }
    System.out.println(str);
  }

  private boolean canEscapeFrom(Position current, Integer[][] escapePlan, List<Position> loop) {
    if (!isValidPos(current)) return true; // case: we left the maze

    boolean canEscape;
    // case: we can escape left or right
    Position left = current.left();
    Position up = current.up();
    if (canEscapeAlready(current, escapePlan)
        || (notBlockingLeft(current, left, loop) && canEscapeAlready(left, escapePlan))
        || (notBlockingUp(current, up, loop) && canEscapeAlready(up, escapePlan))) {
      canEscape = true;
    } else if (blockedAlready(current, escapePlan)) {
      canEscape = false;
    } else {
      Position right = current.right();
      Position down = current.down();
      canEscape = false;
      //      canEscape =
      //          (notBlockingRight(current, right, loop) && canEscapeFrom(right, escapePlan, loop))
      //              || (notBlockingDown(current, down, loop) && canEscapeFrom(down, escapePlan,
      // loop));
    }

    escapePlan[current.getRow()][current.getCol()] = canEscape ? 1 : -1;
    return canEscape;
  }

  private boolean blockedAlready(Position pos, Integer[][] escapePlan) {
    // case: already calculated
    Integer flag = escapePlan[pos.getRow()][pos.getCol()];
    return flag != null && flag == -1;
  }

  private boolean notBlockingUp(Position current, Position up, List<Position> loop) {
    Character c = charAt(current);
    if (!isValidPos(up)) return !loop.contains(current) || c == '|' || c == 'L' || c == 'F';

    Character c2 = charAt(up);
    return !loop.contains(up) || c2 == '|' || c2 == 'L' || c2 == 'F';
  }

  private boolean notBlockingDown(Position current, Position down, List<Position> loop) {
    Character c = charAt(current);
    if (!isValidPos(down)) return !loop.contains(current) || c == '|' || c == 'L' || c == 'F';

    Character c2 = charAt(down);
    return !loop.contains(down) || c2 == '|' || c2 == 'L' || c2 == 'F';
  }

  private boolean notBlockingLeft(Position current, Position left, List<Position> loop) {
    Character c = charAt(current);
    if (!isValidPos(left)) return !loop.contains(current) || c == '-' || c == '7';

    Character c2 = charAt(left);
    return !loop.contains(left) || c2 == '-' || c2 == '7';
  }

  private boolean notBlockingRight(Position current, Position right, List<Position> loop) {
    Character c = charAt(current);
    if (!isValidPos(right)) return !loop.contains(current) || c == '-' || c == 'F';

    Character c2 = charAt(right);
    return !loop.contains(right) || c2 == '-' || c2 == 'F';
  }

  private boolean canEscapeAlready(Position pos, Integer[][] escapePlan) {
    if (!isValidPos(pos)) return true; // case: we left the maze

    // case: already calculated
    Integer flag = escapePlan[pos.getRow()][pos.getCol()];
    return flag != null && flag == 1;
  }
}
