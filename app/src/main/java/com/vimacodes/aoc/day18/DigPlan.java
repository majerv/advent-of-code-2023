package com.vimacodes.aoc.day18;

import java.util.*;
import java.util.stream.Collectors;
import lombok.Value;

@Value
class DigPlan {

  List<DigInstruction> instructions;

  public static DigPlan parse(String text) {
    List<DigInstruction> instructions = text.lines().map(DigInstruction::parse).toList();
    return new DigPlan(instructions);
  }

  public static DigPlan parse2(String text) {
    List<DigInstruction> instructions = text.lines().map(DigInstruction::parse2).toList();
    return new DigPlan(instructions);
  }

  @Override
  public String toString() {
    return instructions.stream().map(DigInstruction::toString).collect(Collectors.joining("\n"));
  }

  public long getTotalLavaP() {
    Point current = new Point(0, 0);
    List<Point> points = new ArrayList<>();
    points.add(current);

    long edges = 0;

    for (DigInstruction inst : instructions) {
      int shiftRow = inst.getDirection().shiftRow;
      int shiftCol = inst.getDirection().shiftCol;

      long digs = inst.getDigs();
      edges += digs;

      Point newPoint =
          new Point(current.getX() + shiftRow * digs, current.getY() + shiftCol * digs);
      current = newPoint;
      points.add(newPoint);
    }

    System.out.println(points);
    System.out.println(edges);

    // Gauss's polygon area formula
    long sum = 0;
    for (int i = 0; i < points.size(); i++) {
      Point point = points.get(i);
      sum += point.getX() * (getPoint(points, i - 1).getY() - getPoint(points, i + 1).getY());
    }

    long area =
        Math.abs(sum) / 2
            + edges / 2
            + 1; // we miss half of the tiles (i.e. the digger goes in the middle of coordinates)
    System.out.println("Area: " + area);

    return area;
  }

  public long getTotalLava() {
    NavigableMap<Integer, NavigableMap<Integer, String>> lagoon = new TreeMap<>();

    int row = 0;
    int col = 0;

    paint(lagoon, row, col, "#000000");

    long edges = 0;
    for (DigInstruction inst : instructions) {
      edges += inst.getDigs();
      for (int k = 0; k < inst.getDigs(); k++) {
        row += inst.getDirection().shiftRow;
        col += inst.getDirection().shiftCol;
        paint(lagoon, row, col, inst.getRgbColorCode());
      }
    }

    prettyPrint(lagoon);

    int total = 0;
    for (var rowMap : lagoon.entrySet()) {
      System.out.println(rowMap.getValue().keySet());
      int inRow = 0;
      NavigableMap<Integer, String> colMap = rowMap.getValue();
      int first = colMap.firstKey();
      int last = colMap.lastKey();

      boolean inside = false;
      boolean lastWasPoint = true;
      for (int j = first; j <= last; j++) {
        if (colMap.containsKey(j)) { // #
          //          ++inRow;
          if (lastWasPoint) {
            inside = !inside;
          }
          lastWasPoint = false;
        } else { // .
          lastWasPoint = true;
          if (inside) {
            ++inRow;
          }
        }
      }
      //      System.out.printf("%d in row\n", inRow);
      //      total += Math.abs(last - first) + 1;
      total += inRow;
    }
    System.out.println("Internal: " + total);
    System.out.println("Edges: " + edges);
    return total + edges;
  }

  private Point getPoint(List<Point> points, int i) {
    return points.get((points.size() + i) % points.size());
  }

  private void prettyPrint(NavigableMap<Integer, NavigableMap<Integer, String>> lagoon) {
    int firstRow = lagoon.firstKey();
    int rows = Math.abs(lagoon.lastKey() - firstRow) + 1;

    int maxCol = Integer.MIN_VALUE;
    int minCol = Integer.MAX_VALUE;
    for (var rowMap : lagoon.entrySet()) {
      var colMap = rowMap.getValue();

      int max = colMap.keySet().stream().mapToInt(i -> i).max().getAsInt();
      int min = colMap.keySet().stream().mapToInt(i -> i).min().getAsInt();

      maxCol = Math.max(maxCol, max);
      minCol = Math.min(minCol, min);
    }
    int cols = Math.abs(maxCol - minCol) + 1;
    int firstCol = minCol;

    System.out.printf("Rows: %d cols: %d, topLeft: (%d,%d)\n", rows, cols, firstRow, firstCol);

    StringBuilder str = new StringBuilder();
    for (int i = firstRow; i < rows; i++) {
      var rowMap = lagoon.get(i);
      if (rowMap == null) {
        str.append("NO ROW");
      } else {
        for (int j = firstCol; j < cols; j++) {
          str.append(rowMap.containsKey(j) ? "#" : ".");
        }
      }
      str.append("\n");
    }
    System.out.println(str);
  }

  private void paint(
      NavigableMap<Integer, NavigableMap<Integer, String>> lagoon, int row, int col, String rgb) {
    //    System.out.printf("Painting: (%d,%d)\n", row, col);
    lagoon.putIfAbsent(row, new TreeMap<>());

    Map<Integer, String> rowMap = lagoon.get(row);
    rowMap.put(col, rgb);
  }
}
