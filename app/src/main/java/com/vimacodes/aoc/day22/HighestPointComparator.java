package com.vimacodes.aoc.day22;

class HighestPointComparator implements java.util.Comparator<Brick> {
  @Override
  public int compare(Brick o1, Brick o2) {
    return o1.getLast().getZ() - o2.getLast().getZ();
  }
}
