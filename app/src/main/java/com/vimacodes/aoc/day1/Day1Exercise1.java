package com.vimacodes.aoc.day1;

public class Day1Exercise1 {
  public int solve(String text) {
    return text.lines()
        .mapToInt(this::lineToCalibrationValue)
        .peek(v -> System.out.println("value: " + v))
        .sum();
  }

  private int lineToCalibrationValue(String line) {
    if (line == null || line.isEmpty()) {
      return 0;
    }

    int first = findFirstDigitOrZero(line);
    int last = findFirstDigitOrZero(reverse(line));

    return first * 10 + last;
  }

  private static Integer findFirstDigitOrZero(String value) {
    return value
        .chars()
        .mapToObj(i -> (char) i)
        .filter(Character::isDigit)
        .findFirst()
        .map(Character::getNumericValue)
        .orElse(0);
  }

  private static String reverse(String value) {
    return new StringBuilder().append(value).reverse().toString();
  }
}
