package com.vimacodes.aoc.day2;

import lombok.Builder;

@Builder
record Cubes(int red, int green, int blue) {
  public boolean isValid() {
    return red >= 0 && green >= 0 && blue >= 0;
  }

  public Cubes subtract(final Cubes other) {
    return Cubes.builder()
        .red(red - other.red)
        .green(green - other.green)
        .blue(blue - other.blue)
        .build();
  }
}
