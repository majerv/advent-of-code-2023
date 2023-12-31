package com.vimacodes.aoc.day22;

public class LowestPointComparator implements java.util.Comparator<Brick> {
  @Override
  public int compare(Brick o1, Brick o2) {
    return o1.getFirst().getZ() - o2.getFirst().getZ();
  }
}
