package com.vimacodes.aoc.day16;

import java.util.*;
import lombok.Value;

@Value
class Contraption {

  int rows;
  int cols;
  List<List<Tile>> tiles;

  public static Contraption parse(String text) {
    List<String> lines = text.lines().toList();
    int rows = lines.size();
    int cols = lines.get(0).length();

    var tiles = lines.stream().map(Contraption::toTiles).toList();
    return new Contraption(rows, cols, tiles);
  }

  public void prettyPrint() {
    StringBuilder str = new StringBuilder();

    for (var tileRow : tiles) {
      for (var tile : tileRow) {
        str.append(tile.surface);
      }

      str.append("  ");
      for (var tile : tileRow) {
        str.append(tile.energized ? '#' : '.');
      }

      str.append("\n");
    }

    System.out.println(str);
  }

  public void energize() {
    Deque<Instruction> instructions = new LinkedList<>();
    instructions.add(new Instruction(0, 0, Direction.RIGHT));

    Set<Instruction> completedInstructions = new HashSet<>();
    Instruction inst;
    while (!instructions.isEmpty()) {
      inst = instructions.removeFirst();
      if (inst.isValid(rows, cols) && !completedInstructions.contains(inst)) {
        Tile tile = tiles.get(inst.getRow()).get(inst.getCol());
        instructions.addAll(tile.energize(inst));
        completedInstructions.add(inst);
//        prettyPrint();
      }
    }
  }

  public long energizedTileCount() {
    return tiles.stream().flatMap(Collection::stream).filter(Tile::isEnergized).count();
  }

  private static List<Tile> toTiles(String line) {
    return line.chars().mapToObj(c -> new Tile((char) c, false)).toList();
  }
}
