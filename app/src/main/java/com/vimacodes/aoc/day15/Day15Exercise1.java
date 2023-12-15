package com.vimacodes.aoc.day15;

import java.util.Arrays;

class Day15Exercise1 {
  public long solve(final String text) {
    return Arrays.stream(text.split(",")).mapToLong(HASHAlgorithm::hash).sum();
  }
}
