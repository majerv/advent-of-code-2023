package com.vimacodes.aoc.day5;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class PlantingPlan {

  Item seed;
  Item soil;
  Item fertilizer;
  Item water;
  Item light;
  Item temperature;
  Item humidity;
  Item location;
}
