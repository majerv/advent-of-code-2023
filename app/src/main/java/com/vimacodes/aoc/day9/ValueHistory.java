package com.vimacodes.aoc.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

record ValueHistory(List<Integer> values) {

  public static ValueHistory parse(String line) {
    List<Integer> values =
        Arrays.stream(line.split("\\s+")).map(String::strip).map(Integer::parseInt).toList();
    return new ValueHistory(values);
  }

  public int extrapolate() {
    System.out.println("Extrapolating: ");
    int result = extrapolate(values, size -> size - 1, Integer::sum);
    System.out.printf("result: %s\n\n", result);
    return result;
  }

  public int extrapolateFirst() {
    System.out.println("Extrapolating: ");
    int result = extrapolate(values, size -> 0, (a, b) -> a - b);
    System.out.printf("result: %s\n\n", result);
    return result;
  }

  private int extrapolate(
      List<Integer> values,
      Function<Integer, Integer> indexLookUp,
      BiFunction<Integer, Integer, Integer> reduce) {
    System.out.println(values);
    List<Integer> diffs = new ArrayList<>();
    boolean allEqual = true;
    int diff;

    for (int i = 0; i < values.size() - 1; i++) {
      diff = values.get(i + 1) - values.get(i);
      diffs.add(diff);
      allEqual &= diff == 0;
    }

    Integer lastElement = values.get(indexLookUp.apply(values.size()));
    return allEqual
        ? lastElement
        : reduce.apply(lastElement, extrapolate(diffs, indexLookUp, reduce));
  }
}
