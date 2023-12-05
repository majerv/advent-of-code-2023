package com.vimacodes.aoc.day5;

import lombok.Value;

@Value
class PlantMapping {
  long sourceRangeStart;
  long destinationRangeStart;
  long rangeLength;

  public long getMapping(long sourceId) {
    long distance = sourceId - sourceRangeStart;
    return destinationRangeStart + distance;
  }

  public boolean matches(long sourceId) {
    return sourceId >= sourceRangeStart && sourceId < sourceRangeStart + rangeLength;
  }
}
