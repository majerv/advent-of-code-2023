package com.vimacodes.aoc.day7;

import java.util.Collection;
import java.util.List;
import lombok.Value;

@Value
class CamelCards {

  Collection<CardHand> hands;

  public static CamelCards parse(String text) {
    List<CardHand> hands = text.lines().map(CardHand::parse).toList();
    return new CamelCards(hands);
  }
}
