package com.vimacodes.aoc.day5;

import java.util.Map;
import java.util.Optional;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
class PlantingInstructions {

  ItemType from;
  ItemType to;

  Map<Long, PlantMapping> instructions;

  public int size() {
    return instructions.size();
  }

  public long getDestination(long sourceId) {
    return getMatchingRange(sourceId).map(r -> r.getMapping(sourceId)).orElse(sourceId);
  }

  public long getDestination(Item source) {
    return getDestination(source.getId());
  }

  private Optional<PlantMapping> getMatchingRange(long sourceId) {
    return instructions.values().stream().filter(m -> m.matches(sourceId)).findFirst();
  }
}
