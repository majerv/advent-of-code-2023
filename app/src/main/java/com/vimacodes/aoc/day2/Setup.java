package com.vimacodes.aoc.day2;

import lombok.Builder;

@Builder
record Setup(Color color, int count) {

  // e.g. 2 green
  public static Setup parse(String line) {
    String[] parts = line.stripLeading().stripTrailing().split("\\s+");
    return Setup.builder()
        .count(Integer.parseInt(parts[0]))
        .color(Color.valueOf(parts[1].toUpperCase()))
        .build();
  }
}
