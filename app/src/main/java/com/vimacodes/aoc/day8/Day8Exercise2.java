package com.vimacodes.aoc.day8;

import java.util.ArrayList;
import java.util.List;

class Day8Exercise2 {
  public long solve(final String text) {
    Network network = Network.parse(text);
    System.out.println(network);

    List<String> startNodes =
        new ArrayList<>(network.getMap().keySet().stream().filter(k -> k.endsWith("A")).toList());

    System.out.println("Start nodes: " + startNodes);

    List<Long> numberOfStepsToZFromEachNode =
        startNodes.stream().map(network::getStepsToZ).toList();

    return lcm(numberOfStepsToZFromEachNode);
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
