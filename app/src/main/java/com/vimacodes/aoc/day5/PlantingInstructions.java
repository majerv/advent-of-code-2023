package com.vimacodes.aoc.day5;

import java.util.NavigableMap;
import java.util.Optional;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
class PlantingInstructions {

  ItemType from;
  ItemType to;

  NavigableMap<Long, PlantMapping> instructions;

  public int size() {
    return instructions.size();
  }

  public long getDestination(long sourceId) {
    //    return getMatchingRange(sourceId).map(r -> r.getMapping(sourceId)).orElse(sourceId);
    return getMatchingRange(sourceId);
  }

  public long getDestination(Item source) {
    return getDestination(source.getId());
  }

  private long getMatchingRange(long sourceId) {
    return Optional.ofNullable(instructions.floorEntry(sourceId))
        .filter(m -> m.getValue().matches(sourceId))
        .map(m -> m.getValue().getMapping(sourceId))
        .orElse(sourceId);
  }
}
