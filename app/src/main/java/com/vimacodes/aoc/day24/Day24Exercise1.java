package com.vimacodes.aoc.day24;

import java.util.List;

/**
 * Based on:
 *
 * <pre>
 * - https://gyires.inf.unideb.hu/KMITT/d03/ch04s02.html and
 * - https://gyires.inf.unideb.hu/KMITT/d03/ch04s04.html
 * </pre>
 */
class Day24Exercise1 {
  public long solve(final String text, final Area area) {
    Hails hails = Hails.parse(text);
    List<HailStone> stones = hails.stones();
    System.out.println(stones);

    long count = 0;
    for (int i = 0; i < stones.size(); i++) {
      HailStone one = stones.get(i);
      for (int j = i + 1; j < stones.size(); j++) {
        HailStone other = stones.get(j);
        Point2d intersection = one.intersectWithIn2d(other);

        if (one.inFuture(intersection)
            && other.inFuture(intersection)
            && area.inside(intersection)) {
          ++count;
        }
      }
    }

    return count;
  }
}
