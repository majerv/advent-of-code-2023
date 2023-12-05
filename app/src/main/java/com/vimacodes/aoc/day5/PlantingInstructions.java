package com.vimacodes.aoc.day5;

import java.util.Map;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
class PlantingInstructions {

  ItemType from;
  ItemType to;

  Map<Integer, PlantMapping> instructions;

  public int size() {
    return instructions.size();
  }
}
