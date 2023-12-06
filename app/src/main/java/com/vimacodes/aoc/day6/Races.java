package com.vimacodes.aoc.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Races {

  public static List<Race> parse(String text) {
    List<String> data = text.lines().toList();

    List<Long> times = parseLongs(data.get(0).split("Time:")[1]);
    List<Long> distances = parseLongs(data.get(1).split("Distance:")[1]);

    List<Race> races = new ArrayList<>();
    for (int i = 0; i < times.size(); i++) {
      races.add(new Race(times.get(i), distances.get(i)));
    }

    return races;
  }

  private static List<Long> parseLongs(String line) {
    return Arrays.stream(line.stripLeading().stripTrailing().split("\\s+"))
        .map(Long::parseLong)
        .toList();
  }
}
