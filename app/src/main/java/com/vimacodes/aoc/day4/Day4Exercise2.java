package com.vimacodes.aoc.day4;

class Day4Exercise2 {

  public long solve(final String text) {
    ScratchCards scratchCards = ScratchCards.parse(text);
    System.out.println(scratchCards);
    return scratchCards.play();
  }
}
