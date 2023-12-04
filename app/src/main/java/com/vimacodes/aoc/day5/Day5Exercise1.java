package com.vimacodes.aoc.day5;

class Day5Exercise1 {
  public long solve(String text) {
    return text.lines()
        .peek(v -> System.out.println("value: " + v))
        .peek(v -> System.out.println("value: " + v))
        .peek(v -> System.out.println("value: " + v))
        .peek(v -> System.out.println("value: " + v))
        .peek(v -> System.out.println("value: " + v))
        .peek(v -> System.out.println("value: " + v))
        .count();
  }
}
