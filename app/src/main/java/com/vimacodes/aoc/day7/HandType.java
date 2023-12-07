package com.vimacodes.aoc.day7;

enum HandType {
  FiveOfAKind(7),
  FourOfaKind(6),
  FullHouse(5),
  ThreeOfAKind(4),
  TwoPair(3),
  OnePair(2),
  HighCard(1);

  HandType(int rank) {
    this.rank = rank;
  }

  final int rank;
}
