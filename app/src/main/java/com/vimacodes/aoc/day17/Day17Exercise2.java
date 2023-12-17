package com.vimacodes.aoc.day17;

class Day17Exercise2 {
  public long solve(final String text) {
    CityMap cityMap = CityMap.parse(text);
    cityMap.prettyPrint();

    return cityMap.minimalHeatLossPathWithUltraCrucibles();
  }
}
