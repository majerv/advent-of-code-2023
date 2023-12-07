package com.vimacodes.aoc.day7;

record Card(Character type) {
  public int rank() {
    return switch (type) {
      case 'A' -> 14;
      case 'K' -> 13;
      case 'Q' -> 12;
      case 'J' -> 11;
      case 'T' -> 10;
      default -> Character.getNumericValue(type);
    };
  }

  public int rankWithJoker() {
    return switch (type) {
      case 'A' -> 14;
      case 'K' -> 13;
      case 'Q' -> 12;
      case 'T' -> 10;
      case 'J' -> 1;
      default -> Character.getNumericValue(type);
    };
  }
}
