package com.vimacodes.aoc.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

record ValueHistory(List<Integer> values) {

  public static ValueHistory parse(String line) {
    List<Integer> values =
        Arrays.stream(line.split("\\s+")).map(String::strip).map(Integer::parseInt).toList();
    return new ValueHistory(values);
  }

  public int extrapolate() {
    System.out.println("Extrapolating: ");
    int result = extrapolate(values);
    System.out.printf("result: %s\n\n", result);
    return result;
  }

  private int extrapolate(List<Integer> values) {
    System.out.println(values);
    List<Integer> diffs = new ArrayList<>();
    boolean allEqual = true;
    int diff;

    for (int i = 0; i < values.size() - 1; i++) {
      diff = values.get(i + 1) - values.get(i);
      diffs.add(diff);
      allEqual &= diff == 0;
    }

    Integer lastElement = values.get(values.size() - 1);
    return allEqual ? lastElement : lastElement + extrapolate(diffs);
  }
}
