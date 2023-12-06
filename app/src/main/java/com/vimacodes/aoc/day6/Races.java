package com.vimacodes.aoc.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Races {

  public static List<Race> parse(String text) {
    List<String> data = text.lines().toList();

    List<Long> times =
        Arrays.stream(data.get(0).split("Time:")[1].stripLeading().stripTrailing().split("\\s+"))
            .map(Long::parseLong)
            .toList();

    List<Long> distances =
        Arrays.stream(
                data.get(1).split("Distance:")[1].stripLeading().stripTrailing().split("\\s+"))
            .map(Long::parseLong)
            .toList();

    List<Race> races = new ArrayList<>();
    for (int i = 0; i < times.size(); i++) {
      races.add(new Race(times.get(i), distances.get(i)));
    }

    return races;
  }
}
