package com.vimacodes.aoc.day11;

import java.util.*;
import java.util.stream.Collectors;
import lombok.Value;

@Value
class Universe {

  int rows;
  int cols;
  Character[][] universe;
  Map<Integer, Position> galaxies;
  Map<Position, Integer> galaxyLocations;

  public static Universe parse(String text) {
    List<String> lines = text.lines().toList();
    int rows = lines.size();
    int cols = lines.get(0).length();

    Character[][] universe = new Character[rows][cols];
    Map<Integer, Position> galaxies = new HashMap<>();
    Map<Position, Integer> galaxyLocations = new HashMap<>();

    String line;
    int galaxyCounter = 0;
    for (int i = 0; i < rows; i++) {
      line = lines.get(i);
      for (int j = 0; j < cols; j++) {
        char c = line.charAt(j);
        universe[i][j] = c;
        if (c == '#') {
          int count = ++galaxyCounter;
          Position value = new Position(i, j);
          galaxies.put(count, value);
          galaxyLocations.put(value, count);
        }
      }
    }

    return new Universe(rows, cols, universe, galaxies, galaxyLocations);
  }

  public Universe expand() {
    return Universe.parse(printToExpandedUniverse());
  }

  public Universe expand(int factor) {
    return Universe.parse(printToExpandedUniverse());
  }

  public String printToExpandedUniverse() {
    Set<Integer> rowsWithGalaxies =
        galaxyLocations.keySet().stream().map(Position::getRow).collect(Collectors.toSet());
    Set<Integer> colsWithGalaxies =
        galaxyLocations.keySet().stream().map(Position::getCol).collect(Collectors.toSet());

    StringBuilder str = new StringBuilder();
    for (int i = 0; i < rows; i++) {
      StringBuilder line = new StringBuilder();
      for (int j = 0; j < cols; j++) {
        Character c = universe[i][j];
        line.append(c);
        if (!colsWithGalaxies.contains(j)) line.append(c);
      }
      line.append("\n");
      str.append(line);
      if (!rowsWithGalaxies.contains(i)) str.append(line);
    }
    return str.toString();
  }

  public Map<Pair, Integer> apsp() {
    Map<Pair, Integer> galaxyPairDistances = new HashMap<>();
    int N = galaxies.keySet().size();
    for (int i = 1; i <= N; i++) {
      for (int j = i; j <= N; j++) {
        if (i == j) continue;
        Integer distance = distanceOf(i, j);
        //        System.out.printf("Between galaxy %d and galaxy %d: %d\n", i, j, distance);
        galaxyPairDistances.put(new Pair(i, j), distance);
      }
    }

    return galaxyPairDistances;
  }

  private Integer distanceOf(int galaxyId, int otherGalaxyId) {
    Position pos1 = galaxies.get(galaxyId);
    Position pos2 = galaxies.get(otherGalaxyId);

    return Math.abs(pos1.getRow() - pos2.getRow()) + Math.abs(pos1.getCol() - pos2.getCol());
  }

  public String prettyPrint() {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        str.append(universe[i][j]);
      }
      str.append("\n");
    }
    return str.toString();
  }

  public String prettyPrintWithNumbers() {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        Position pos = new Position(i, j);
        if (galaxyLocations.containsKey(pos)) str.append(galaxyLocations.get(pos));
        else str.append(universe[i][j]);
      }
      str.append("\n");
    }
    return str.toString();
  }
}
