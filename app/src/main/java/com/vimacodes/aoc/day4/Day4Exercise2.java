package com.vimacodes.aoc.day4;

class Day4Exercise2 {

  public long solve(final String text) {
    return text.lines().map(Card::parse).peek(System.out::println).mapToInt(Card::getPoints).sum();
  }
}
