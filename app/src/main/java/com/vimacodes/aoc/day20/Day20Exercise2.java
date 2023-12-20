package com.vimacodes.aoc.day20;

import java.util.List;

class Day20Exercise2 {
  public long solve(final String text) {
    ModuleConfiguration moduleConfiguration = ModuleConfiguration.parse(text);
    System.out.println(moduleConfiguration);

    List<String> targetDestinations = List.of("sg", "lm", "dh", "db");
    String destination = "jm";

    List<Long> numberOfPressesToEachDestination =
        targetDestinations.stream()
            .map(source -> moduleConfiguration.getPressesTilHighPulse(source, destination))
            .toList();

    return lcm(numberOfPressesToEachDestination);
  }

  private long lcm(List<Long> numbers) {
    long lcm = numbers.get(0);
    for (int i = 1; i < numbers.size(); i++) {
      lcm = lcm(lcm, numbers.get(i));
    }
    return lcm;
  }

  private static long lcm(long number1, long number2) {
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
