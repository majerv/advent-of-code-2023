package com.vimacodes.aoc.day7;

import java.util.List;
import java.util.stream.IntStream;

class Day7Exercise1 {
  public long solve(final String text) {
    CamelCards camelCards = CamelCards.parse(text);

    List<CardHand> handsInOrder =
        camelCards.getHands().stream().sorted(new CardHandComparator()).toList();

    return IntStream.range(0, handsInOrder.size()).map(i -> getWinning(i, handsInOrder)).sum();
  }

  private static int getWinning(int i, List<CardHand> handsInOrder) {
    int rank = i + 1;
    return handsInOrder.get(i).getBid() * rank;
  }
}
