package com.vimacodes.aoc.day4;

class Day4Exercise1 {

  public long solve(final String text) {
    return text.lines()
        .map(Card::parse)
        .peek(card -> System.out.println("card: " + card))
        .mapToInt(Card::getPoints)
        .sum();
  }
}
