package com.vimacodes.aoc.day7;

import java.util.Comparator;

public class CardHandComparator implements Comparator<CardHand> {
  @Override
  public int compare(CardHand hand1, CardHand hand2) {
    int rank1 = hand1.getType().rank;
    int rank2 = hand2.getType().rank;

    int diff = rank1 - rank2;
    return diff == 0 ? compareByDifferentCard(hand1, hand2) : diff;
  }

  private int compareByDifferentCard(CardHand hand1, CardHand hand2) {
    for (int i = 0; i < 5; i++) {
      int rank1 = hand1.getCards().get(i).rank();
      int rank2 = hand2.getCards().get(i).rank();

      if (rank1 != rank2) return rank1 - rank2;
    }
    return 0;
  }
}
