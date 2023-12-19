package com.vimacodes.aoc.day19;

import lombok.Value;

@Value
class MachinePart {
  long x;
  long m;
  long a;
  long s;

  public long getOverallRating() {
    return x + m + a + s;
  }

  public static MachinePart parse(String line) {
    String[] parts = line.split(",");

    long x = parseComponent(parts, 0);
    long m = parseComponent(parts, 1);
    long a = parseComponent(parts, 2);
    long s = parseComponent(parts, 3);

    return new MachinePart(x, m, a, s);
  }

  private static long parseComponent(String[] parts, int index) {
    return Long.parseLong(parts[index].split("=")[1].replaceAll("}", "").trim());
  }

  public long getComponent(String component) {
    return switch (component) {
      case "x" -> x;
      case "m" -> m;
      case "a" -> a;
      case "s" -> s;
      default -> throw new IllegalArgumentException("No such component: " + component);
    };
  }
}
