package com.vimacodes.aoc.day7;

import com.google.common.annotations.VisibleForTesting;
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
  private final HandType typeWithJokers;
  private final String representation;

  public CardHand(String representation, List<Card> cards, int bid) {
    this.representation = representation;
    this.cards = cards;
    this.bid = bid;
    type = evaluate(cards);
    typeWithJokers = evaluateWithJokers(cards);

    boolean hasJoker = cards.stream().anyMatch(c -> c.type() == 'J');
    if (hasJoker) {
      System.out.printf(
          "Cards: %s hand: [%s] handWithJokers: [%s]\n", representation, type, typeWithJokers);
    }
  }

  private static HandType evaluate(List<Card> cards) {
    Map<Character, Integer> countByCardType = buildStats(cards);
    List<Integer> sortedStats = countByCardType.values().stream().sorted().toList();
    return evalStats(sortedStats);
  }

  @VisibleForTesting
  static HandType evaluateWithJokers(List<Card> cards) {
    Map<Character, Integer> countByCardType = buildStats(cards);
    Integer jokers = countByCardType.get('J');

    if (jokers == null || jokers == 0) {
      List<Integer> sortedStats = countByCardType.values().stream().sorted().toList();
      return evalStats(sortedStats);
    }

    List<Integer> sortedStats =
        countByCardType.entrySet().stream()
            .filter(e -> e.getKey() != 'J')
            .map(Map.Entry::getValue)
            .sorted()
            .toList();

    return applyJokers(jokers, sortedStats);
  }

  private static HandType applyJokers(Integer jokers, List<Integer> sortedStats) {
    if (jokers > 3) return HandType.FiveOfAKind;
    int statSize = sortedStats.size();
    if (jokers == 3) {
      return statSize == 2 ? HandType.FourOfAKind : HandType.FiveOfAKind;
    }
    if (jokers == 2) {
      if (statSize == 1) return HandType.FiveOfAKind;
      if (statSize == 2) return HandType.FourOfAKind;
      else return HandType.ThreeOfAKind;
    }

    // 1 joker only
    if (statSize == 1) return HandType.FiveOfAKind;
    if (statSize == 2) {
      return sortedStats.get(1) == 2 ? HandType.FullHouse : HandType.FourOfAKind;
    }
    if (statSize == 3) return HandType.ThreeOfAKind;

    return HandType.OnePair;
  }

  private static HandType evalStats(List<Integer> sortedStats) {
    int statSize = sortedStats.size();
    if (statSize == 1) return HandType.FiveOfAKind;
    if (statSize == 2) {
      if (sortedStats.get(0) == 4 || sortedStats.get(1) == 4) return HandType.FourOfAKind;
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

    String representation = parts[0];
    List<Card> cards = toCardList(representation);
    int bid = Integer.parseInt(parts[1]);

    return new CardHand(representation, cards, bid);
  }

  @VisibleForTesting
  static List<Card> toCardList(String representation) {
    return representation.chars().mapToObj(i -> new Card((char) i)).toList();
  }

  private static Map<Character, Integer> buildStats(List<Card> cards) {
    Map<Character, Integer> countByCardType = new HashMap<>();

    for (Card card : cards) {
      Character type = card.type();

      var result = countByCardType.computeIfPresent(type, (k, old) -> ++old);
      if (result == null) countByCardType.put(type, 1);
    }

    return countByCardType;
  }
}
