package com.vimacodes.aoc.day15;

import java.util.Arrays;

class Day15Exercise1 {
  public long solve(final String text) {
    String[] parts = text.split("\\n");

    return Arrays.stream(text.split(",")).mapToLong(HASHAlgorithm::hash).sum();
  }
}
