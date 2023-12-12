package com.vimacodes.aoc.day12;

import java.util.Arrays;
import java.util.List;
import lombok.Value;

@Value
class ConditionRecords {
  String condition;
  List<Integer> errorGroups;

  public static ConditionRecords parse(String line) {
    String[] parts = line.split(" ");
    return new ConditionRecords(
        parts[0], Arrays.stream(parts[1].split(",")).map(Integer::parseInt).toList());
  }

  public int arrangements() {
    return arrangements(condition, errorGroups);
  }

  private int arrangements(String condition, List<Integer> errorGroups) {
    if (condition.isEmpty()) {
      return errorGroups.isEmpty() ? 1 : 0;
    }

    if (errorGroups.isEmpty()) {
      return condition.contains("#") ? 0 : 1;
    }

    int arrangements = 0;
    char c = condition.charAt(0);

    if (c == '.' || c == '?') arrangements += arrangements(condition.substring(1), errorGroups);

    if (c == '#' || c == '?') {
      Integer num = errorGroups.get(0);
      if (condition.length() >= num
          && !condition.substring(0, num).contains(".")
          && (num == condition.length() || condition.charAt(num) != '#')) {
        arrangements +=
            arrangements(
                substrFrom(condition, num + 1), errorGroups.subList(1, errorGroups.size()));
      }
    }

    return arrangements;
  }

  private String substrFrom(String condition, int index) {
    return index >= condition.length() ? "" : condition.substring(index);
  }
}
