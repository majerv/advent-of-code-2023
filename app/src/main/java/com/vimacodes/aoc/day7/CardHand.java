package com.vimacodes.aoc.day7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
class CardHand {

  private final List<Card> cards;
  private final int bid;
  private final HandType type;

  public CardHand(List<Card> cards, int bid) {
    this.cards = cards;
    this.bid = bid;
    type = evaluate(cards);
  }

  private static HandType evaluate(List<Card> cards) {
    Map<Character, Integer> countByCardType = new HashMap<>();

    for (Card card : cards) {
      Character type = card.type();

      var result = countByCardType.computeIfPresent(type, (k, old) -> ++old);
      if (result == null) countByCardType.put(type, 1);
    }

    List<Integer> sortedStats = countByCardType.values().stream().sorted().toList();
    HandType handType = evalStats(sortedStats);
    System.out.printf("Cards: %s sorted: %s type: %s\n", cards, sortedStats, handType);

    return handType;
  }

  private static HandType evalStats(List<Integer> sortedStats) {
    int statSize = sortedStats.size();
    if (statSize == 1) return HandType.FiveOfAKind;
    if (statSize == 2) {
      if (sortedStats.get(0) == 4 || sortedStats.get(1) == 4) return HandType.FourOfaKind;
      else return HandType.FullHouse;
    }
    if (statSize == 3) {
      return sortedStats.get(2) == 3 ? HandType.ThreeOfAKind : HandType.TwoPair;
    }
    if (statSize == 4) return HandType.OnePair;

    return HandType.HighCard;
  }

  public static CardHand parse(String line) {
    String[] parts = line.split("\\s+");

    List<Card> cards = parts[0].chars().mapToObj(i -> new Card((char) i)).toList();
    int bid = Integer.parseInt(parts[1]);

    return new CardHand(cards, bid);
  }
}
