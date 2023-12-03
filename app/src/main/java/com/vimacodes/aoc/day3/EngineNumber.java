package com.vimacodes.aoc.day3;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class EngineNumber {

  int number;
  Position start;
  Position end;
}
