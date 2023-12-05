package com.vimacodes.aoc.day5;

import java.util.List;

class Day5Exercise2 {
  public long solve(String text) {
    Almanac almanac = Almanac.parse(text);
    List<Item> seeds = almanac.seeds();

    long min = Long.MAX_VALUE;

    for (int i = 0; i < seeds.size(); i += 2) {
      long start = seeds.get(i).getId();
      long rangeLength = seeds.get(i + 1).getId();
      for (long j = start; j < start + rangeLength; ++j) {
        long locationId = almanac.getPlantingPlan(j).getLocation().getId();
        if (locationId < min) min = locationId;
      }
    }

    return min;
  }
}
