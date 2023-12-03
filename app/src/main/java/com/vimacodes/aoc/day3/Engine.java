package com.vimacodes.aoc.day3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.Value;

@Value
class Engine {

  int rows;
  int cols;
  List<String> scheme;

  public static Engine parse(@NonNull final String engineScheme) {
    List<String> lines = engineScheme.lines().toList();
    return new Engine(lines.size(), lines.get(0).length(), lines);
  }

  public List<EngineNumber> numbers() {
    List<EngineNumber> numbers = new ArrayList<>();

    for (int i = 0; i < scheme.size(); i++) {
      Collection<EngineNumber> c = collectNumbers(i);
      numbers.addAll(c);
      System.out.printf(
          "Numbers in row [%s]: %s\n",
          i,
          c.stream()
              .map(EngineNumber::getNumber)
              .map(String::valueOf)
              .collect(Collectors.joining(",")));
    }

    return numbers;
  }

  private Collection<EngineNumber> collectNumbers(int row) {
    if (row < 0 || row >= rows) return Collections.emptyList();

    List<EngineNumber> numbers = new ArrayList<>();
    String line = scheme.get(row);

    int start = 0;
    int end = 0;
    StringBuilder num = new StringBuilder();

    for (int col = 0; col < line.length(); ++col) {
      char ch = line.charAt(col);
      if (Character.isDigit(ch)) {
        num.append(ch);
        end = col;
      } else {
        addResult(row, num, numbers, start, end);
        num = new StringBuilder();
        start = col + 1;
      }
    }

    addResult(row, num, numbers, start, end);

    return numbers;
  }

  private static void addResult(
      int row, StringBuilder num, List<EngineNumber> numbers, int start, int end) {
    var numString = num.toString();
    if (!num.toString().isBlank()) {
      numbers.add(
          new EngineNumber(
              Integer.parseInt(numString), new Position(row, start), new Position(row, end)));
    }
  }

  public boolean isAdjacentToSymbol(EngineNumber number) {
    //    System.out.println("CHECKING: " + number);
    List<Position> symbols =
        number.neighbourPositions().stream().filter(p -> isValid(p) && isSymbol(p)).toList();

    //    System.out.printf("%s has neighbouring symbols: %s\n", number, symbols);

    return !symbols.isEmpty();
  }

  private boolean isSymbol(Position p) {
    char c = scheme.get(p.row()).charAt(p.col());
    boolean b = !Character.isDigit(c) && c != '.';
    //    if (b) System.out.println("SYMBOL FOUND: " + c);
    return b;
  }

  private boolean isValid(Position p) {
    return p.row() >= 0 && p.row() < rows && p.col() >= 0 && p.col() < cols;
  }

  public Collection<Gear> gears() {
    List<Gear> gears = new ArrayList<>();

    for (int i = 0; i < scheme.size(); i++) {
      gears.addAll(collectGears(i));
    }

    return gears;
  }

  private Collection<Gear> collectGears(int row) {
    List<Gear> gears = new ArrayList<>();
    String line = scheme.get(row);

    for (int col = 0; col < line.length(); ++col) {
      if (line.charAt(col) == '*') {
        List<Integer> adjacent = collectAdjacentNumbers(row, col);
        if (adjacent.size() == 2) {
          gears.add(new Gear(adjacent.get(0), adjacent.get(1)));
        }
      }
    }

    return gears;
  }

  private List<Integer> collectAdjacentNumbers(int row, int col) {
    Position position = new Position(row, col);

    List<EngineNumber> candidates = new ArrayList<>();
    candidates.addAll(collectNumbers(row - 1));
    candidates.addAll(collectNumbers(row));
    candidates.addAll(collectNumbers(row + 1));

    return candidates.stream()
        .filter(n -> n.isAdjacentTo(position))
        .map(EngineNumber::getNumber)
        .toList();
  }
}
