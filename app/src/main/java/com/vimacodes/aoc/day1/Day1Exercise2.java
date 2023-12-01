package com.vimacodes.aoc.day1;

import java.util.Map;
import javax.annotation.Nonnull;

public class Day1Exercise2 {

  private static final Map<String, String> DIGITS_AND_PREFIXES =
      Map.of(
          "on", "one",
          "tw", "two",
          "th", "three",
          "fo", "four",
          "fi", "five",
          "si", "six",
          "se", "seven",
          "ei", "eight",
          "ni", "nine");

  private static final Map<String, String> REVERSE_DIGITS_AND_PREFIXES =
      Map.of(
          "eno", "eno",
          "owt", "owt",
          "eer", "eerht",
          "ruo", "ruof",
          "evi", "evif",
          "xis", "xis",
          "nev", "neves",
          "thg", "thgie",
          "eni", "enin");

  public long solve(@Nonnull String text) {
    long sum =
        text.lines()
            .mapToInt(this::lineToCalibrationValue)
            .peek(v -> System.out.println("value: " + v))
            .sum();
    System.out.println("SUM: " + sum);
    return sum;
  }

  // FIXME: refactor the duplicate methods (only reverse is different + prefixes)
  public String resolveNumbers(String text) {
    System.out.println("Resolving: " + text);
    int i = 0;
    var result = new StringBuilder();
    char ch, ch2;
    boolean replacedLast = false;

    while (i < text.length() - 1) {
      ch = text.charAt(i);
      ch2 = text.charAt(i + 1);

      var number = DIGITS_AND_PREFIXES.get(ch + "" + ch2);
      if (number != null
          && i <= text.length() - number.length()
          && text.substring(i, i + number.length()).equals(number)) {
        result.append(englishNumberToInteger(number));
        replacedLast = i + number.length() == text.length();
        i += number.length();
      } else {
        result.append(ch);
        ++i;
      }
    }
    // last character
    if (!replacedLast) {
      result.append(text.charAt(text.length() - 1));
    }

    String resultString = result.toString();
    System.out.println("Resolved: " + resultString);
    return resultString;
  }

  public String resolveNumbersReverse(String text) {
    if (text.length() < 3) {
      System.out.println("Resolving reverse: " + text);
      System.out.println("Resolved reverse: " + text);
      return text;
    }

    System.out.println("Resolving reverse: " + text);
    int i = 0;
    var result = new StringBuilder();
    char ch, ch2, ch3;
    boolean replacedLast = false;

    while (i < text.length() - 2) {
      ch = text.charAt(i);
      ch2 = text.charAt(i + 1);
      ch3 = text.charAt(i + 2);

      var number = REVERSE_DIGITS_AND_PREFIXES.get(ch + "" + ch2 + ch3);
      if (number != null
          && i <= text.length() - number.length()
          && text.substring(i, i + number.length()).equals(number)) {
        result.append(englishNumberToInteger(reverse(number)));
        replacedLast = i + number.length() == text.length();
        i += number.length();
      } else {
        result.append(ch);
        ++i;
      }
    }
    // last character
    if (!replacedLast) {
      result.append(text.charAt(text.length() - 2));
      result.append(text.charAt(text.length() - 1));
    }

    String resultString = result.toString();
    System.out.println("Resolved reverse: " + resultString);
    return resultString;
  }

  public int lineToCalibrationValue(String line) {
    if (line == null || line.isEmpty()) {
      return 0;
    }

    int first = findFirstDigitOrZero(resolveNumbers(line));
    int last = findFirstDigitOrZero(resolveNumbersReverse(reverse(line)));

    System.out.println("First: %s, last: %s".formatted(first, last));

    if (first == 0) {
      first = findFirstDigitOrZero(resolveNumbers(reverse(line)));
    }

    if (last == 0) {
      last = findFirstDigitOrZero(resolveNumbersReverse(line));
    }

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

  private static int englishNumberToInteger(String number) {
    return switch (number) {
      case "one" -> 1;
      case "two" -> 2;
      case "three" -> 3;
      case "four" -> 4;
      case "five" -> 5;
      case "six" -> 6;
      case "seven" -> 7;
      case "eight" -> 8;
      case "nine" -> 9;
      default -> 0;
    };
  }
}
