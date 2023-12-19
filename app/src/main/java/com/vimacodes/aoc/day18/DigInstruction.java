package com.vimacodes.aoc.day18;

import lombok.Value;

@Value
class DigInstruction {

  Direction direction;
  long digs;
  String rgbColorCode;

  public static DigInstruction parse(String line) {
    String[] parts = line.trim().split("\\s+");
    Direction direction = Direction.valueOf(parts[0]);
    int digs = Integer.parseInt(parts[1]);
    String rgb = parts[2].substring(1, parts[2].length() - 1);

    return new DigInstruction(direction, digs, rgb);
  }

  @Override
  public String toString() {
    return "%s %d %s".formatted(direction, digs, rgbColorCode);
  }

  public static DigInstruction parse2(String line) {
    var inst = parse(line);

    String rgb = inst.rgbColorCode;
    var newDirection = directionOf(rgb.substring(rgb.length() - 1));
    var newDigs = Long.parseLong(rgb.substring(1, rgb.length() - 1), 16);

    return new DigInstruction(newDirection, newDigs, rgb);
  }

  private static Direction directionOf(String str) {
    return switch (str) {
      case "0" -> Direction.R;
      case "1" -> Direction.D;
      case "2" -> Direction.L;
      case "3" -> Direction.U;
      default -> throw new IllegalArgumentException();
    };
  }
}
