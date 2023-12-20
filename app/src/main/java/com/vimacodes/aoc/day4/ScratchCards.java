package com.vimacodes.aoc.day4;

import java.util.*;

class ScratchCards {

  List<Card> cards;
  Map<Integer, Integer> idToCommon;

  public static ScratchCards parse(String text) {
    List<Card> cards = text.lines().map(Card::parse).toList();
    return new ScratchCards(cards);
  }

  ScratchCards(List<Card> cards) {
    this.cards = cards;

    idToCommon = new HashMap<>();
    for (Card card : cards) {
      idToCommon.put(card.getId(), card.getCommonNumbers().size());
    }
  }

  // REFACTORED WITH DYN PROG! - see below :)))
  public int playBruteForce() {
    int cardsPlayed = 0;
    List<Integer> toPlay = new ArrayList<>(idToCommon.keySet().stream().toList());

    while (!toPlay.isEmpty()) {
      int cardId = toPlay.remove(0);
      ++cardsPlayed;
      //      System.out.println("Playing: " + cardId);

      int cardsWon = idToCommon.get(cardId);
      //      System.out.println("Cards won: " + cardsWon);
      if (cardsWon > 0) {
        for (int i = 0; i < cardsWon; i++) {
          toPlay.add(cardId + i + 1);
        }
      }
    }

    return cardsPlayed;
  }

  public int play() {
    int cardsWon = 0;
    Map<Integer, Integer> memo = new HashMap<>();

    for (int i = 1; i <= idToCommon.size(); i++) {
      cardsWon += cardsWon(i, memo);
    }

    return cardsWon;
  }

  private int cardsWon(int cardId, Map<Integer, Integer> memo) {
    if (memo.containsKey(cardId)) {
      return memo.get(cardId);
    }

    int cardsWon = idToCommon.get(cardId);
    int total = 1;

    for (int i = cardId + 1; i < cardId + cardsWon + 1; i++) {
      total += cardsWon(i, memo);
    }

    memo.put(cardId, total);
    return total;
  }
}
