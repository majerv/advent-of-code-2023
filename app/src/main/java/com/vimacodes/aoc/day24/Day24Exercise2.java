package com.vimacodes.aoc.day24;

import java.util.List;

/**
 * Based on:
 *
 * <pre>
 * - https://gyires.inf.unideb.hu/KMITT/d03/ch04s02.html and
 * - https://gyires.inf.unideb.hu/KMITT/d03/ch04s04.html
 * </pre>
 *
 * and calculations on paper (see in the /img folder)
 */
class Day24Exercise2 {
  public long solve(final String text) {
    Hails hails = Hails.parse(text);
    List<HailStone> stones = hails.stones();
    System.out.println(stones);

    HailStone rock = hails.calculateRockThatHitsAll();
    Point3d position = rock.getPosition();

    return (long) (position.getX() + position.getY() + position.getZ());
  }
}
