package com.vimacodes.aoc.day11;

import java.util.*;
import lombok.Value;

@Value
class ExpandedUniverse {

  Map<Integer, Position> galaxies;

  public Map<Pair, Integer> apsp() {
    Map<Pair, Integer> galaxyPairDistances = new HashMap<>();
    int N = galaxies.keySet().size();
    for (int i = 1; i <= N; i++) {
      for (int j = i; j <= N; j++) {
        if (i == j) continue;
        Integer distance = distanceOf(i, j);
        //        System.out.printf("Expanded: Between galaxy %d and galaxy %d: %d\n", i, j,
        // distance);
        galaxyPairDistances.put(new Pair(i, j), distance);
      }
    }

    return galaxyPairDistances;
  }

  private Integer distanceOf(int galaxyId, int otherGalaxyId) {
    Position pos1 = galaxies.get(galaxyId);
    Position pos2 = galaxies.get(otherGalaxyId);

    return Math.abs(pos1.getRow() - pos2.getRow()) + Math.abs(pos1.getCol() - pos2.getCol());
  }
}
