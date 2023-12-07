package com.vimacodes.aoc.day7;

public class CardHandComparatorWithJoker implements java.util.Comparator<CardHand> {
  @Override
  public int compare(CardHand hand1, CardHand hand2) {
    int rank1 = hand1.getTypeWithJokers().rank;
    int rank2 = hand2.getTypeWithJokers().rank;

    int diff = rank1 - rank2;
    return diff == 0 ? compareByDifferentCard(hand1, hand2) : diff;
  }

  public int compareByDifferentCard(CardHand hand1, CardHand hand2) {
    for (int i = 0; i < 5; i++) {
      int rank1 = hand1.getCards().get(i).rankWithJoker();
      int rank2 = hand2.getCards().get(i).rankWithJoker();

      if (rank1 != rank2) return rank1 - rank2;
    }
    return 0;
  }
}
