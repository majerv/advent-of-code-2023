package com.vimacodes.aoc.day17;

import java.util.Comparator;
import lombok.Value;

@Value
class Move {
  int heatLoss;
  Instruction instruction;

  public static class HeatLossComparator implements Comparator<Move> {
    @Override
    public int compare(Move o1, Move o2) {
      return o1.heatLoss - o2.heatLoss;
    }
  }
}
