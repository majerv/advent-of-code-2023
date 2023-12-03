package com.vimacodes.aoc.day3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
      System.out.printf("Numbers in row [%s]: %s\n", i, c);
    }

    return numbers;
  }

  private Collection<EngineNumber> collectNumbers(int row) {
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
        String numString = num.toString();
        if (!numString.isBlank()) {
          numbers.add(
              new EngineNumber(
                  Integer.parseInt(numString), new Position(row, start), new Position(row, end)));
        }
        num = new StringBuilder();
        start = col + 1;
        //        end = start;
      }
    }

    return numbers;
  }

  public boolean isAdjacentToSymbol(EngineNumber number) {
    System.out.println("CHECKING: " + number);
    List<Position> symbols =
        number.neighbourPositions().stream().filter(p -> isValid(p) && isSymbol(p)).toList();

    System.out.printf("%s has neighbouring symbols: %s\n", number, symbols);

    return !symbols.isEmpty();
  }

  private boolean isSymbol(Position p) {
    char c = scheme.get(p.row()).charAt(p.col());
    return !Character.isDigit(c) && c != '.';
  }

  private boolean isValid(Position p) {
    return p.row() >= 0 && p.row() < rows && p.col() >= 0 && p.col() < cols;
  }
}
