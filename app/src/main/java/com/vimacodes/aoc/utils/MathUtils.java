package com.vimacodes.aoc.utils;

import java.util.List;

public class MathUtils {

  private MathUtils() {}

  public static long lcm(List<Long> numbers) {
    long lcm = numbers.get(0);
    for (int i = 1; i < numbers.size(); i++) {
      lcm = lcm(lcm, numbers.get(i));
    }
    return lcm;
  }

  public static long lcm(long number1, long number2) {
    if (number1 == 0 || number2 == 0) {
      return 0;
    }
    long absNumber1 = Math.abs(number1);
    long absNumber2 = Math.abs(number2);
    long absHigherNumber = Math.max(absNumber1, absNumber2);
    long absLowerNumber = Math.min(absNumber1, absNumber2);
    long lcm = absHigherNumber;
    while (lcm % absLowerNumber != 0) {
      lcm += absHigherNumber;
    }
    return lcm;
  }
}
