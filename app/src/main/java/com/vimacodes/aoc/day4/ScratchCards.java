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

  // TO BE REFACTORED WITH DYN PROG! :)))
  public int play() {
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

  private Collection<Card> getCardsFrom(int index, int numberOfCards) {
    return cards.subList(index, index + numberOfCards);
  }
}
