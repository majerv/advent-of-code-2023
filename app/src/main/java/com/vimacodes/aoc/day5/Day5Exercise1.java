package com.vimacodes.aoc.day5;

class Day5Exercise1 {
  public long solve(String text) {
    Almanac almanac = Almanac.parse(text);

    return almanac.seeds().stream()
        .map(almanac::getPlantingPlan)
        .map(PlantingPlan::getLocation)
        .mapToLong(Item::getId)
        .min()
        .orElse(0);
  }
}
