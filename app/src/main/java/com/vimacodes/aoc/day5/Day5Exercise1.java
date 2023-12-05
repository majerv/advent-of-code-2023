package com.vimacodes.aoc.day5;

class Day5Exercise1 {
  public int solve(String text) {
    Almanac almanac = Almanac.parse(text);

    return almanac.seeds().stream()
        .peek(System.out::println)
        .map(Almanac::getPlantingPlan)
        .map(PlantingPlan::getLocation)
        .mapToInt(Item::getId)
        .min()
        .orElse(0);
  }
}
