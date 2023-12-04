package com.vimacodes.aoc.day4;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Value;

@Value
class Card {

  String id;
  List<Integer> winningNumbers;
  List<Integer> numbers;

  public static Card parse(String line) {
    String[] game = line.split(":");
    String rawNumbers = game[1];
    String[] parts = rawNumbers.split("\\|");

    return new Card(game[0], parseInts(parts, 0), parseInts(parts, 1));
  }

  public int getPoints() {
    Set<Integer> commonNumbers = getCommonNumbers();
    return (int) Math.pow(2, commonNumbers.size() - 1);
  }

  private Set<Integer> getCommonNumbers() {
    return winningNumbers.stream().distinct().filter(numbers::contains).collect(Collectors.toSet());
  }

  private static List<Integer> parseInts(String[] parts, int x) {
    return Arrays.stream(parts[x].stripLeading().stripLeading().split("\\s+"))
        .map(Integer::parseInt)
        .toList();
  }
}
