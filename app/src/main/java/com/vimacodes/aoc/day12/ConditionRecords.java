package com.vimacodes.aoc.day12;

import java.util.*;
import lombok.Value;

@Value
class ConditionRecords {
  private static final int DEFAULT_UNFOLD_FACTOR = 5;

  String condition;
  List<Integer> errorGroups;

  public static ConditionRecords parse(String line) {
    String[] parts = line.split(" ");
    return new ConditionRecords(
        parts[0], Arrays.stream(parts[1].split(",")).map(Integer::parseInt).toList());
  }

  public long arrangements() {
    Map<ConditionRecords, Long> memo = new HashMap<>();
    return arrangements(this, memo);
  }

  private static long arrangements(ConditionRecords records, Map<ConditionRecords, Long> memo) {
    String condition = records.getCondition();
    List<Integer> errorGroups = records.getErrorGroups();

    if (condition.isEmpty()) {
      return errorGroups.isEmpty() ? 1 : 0;
    }

    if (errorGroups.isEmpty()) {
      return condition.contains("#") ? 0 : 1;
    }

    if (memo.containsKey(records)) {
      return memo.get(records);
    }

    long arrangements = 0;
    char c = condition.charAt(0);

    if (c == '.' || c == '?') {
      arrangements += arrangements(new ConditionRecords(condition.substring(1), errorGroups), memo);
    }

    if (c == '#' || c == '?') {
      Integer num = errorGroups.get(0);
      if (condition.length() >= num
          && !condition.substring(0, num).contains(".")
          && (num == condition.length() || condition.charAt(num) != '#')) {
        arrangements +=
            arrangements(
                new ConditionRecords(
                    substrFrom(condition, num + 1), errorGroups.subList(1, errorGroups.size())),
                memo);
      }
    }

    memo.put(records, arrangements);
    return arrangements;
  }

  private static String substrFrom(String condition, int index) {
    return index >= condition.length() ? "" : condition.substring(index);
  }

  public ConditionRecords unfold() {
    return unfold(DEFAULT_UNFOLD_FACTOR);
  }

  public ConditionRecords unfold(final int factor) {
    StringBuilder newCondition = new StringBuilder();
    List<Integer> newErrorGroups = new ArrayList<>();

    for (int i = 0; i < factor; i++) {
      newCondition.append(condition);
      if (i != factor - 1) {
        newCondition.append("?");
      }

      newErrorGroups.addAll(errorGroups);
    }

    return new ConditionRecords(newCondition.toString(), newErrorGroups);
  }
}
