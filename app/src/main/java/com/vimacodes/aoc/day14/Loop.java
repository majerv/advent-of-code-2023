package com.vimacodes.aoc.day14;

import java.util.Map;
import lombok.Value;

@Value
class Loop {
  int startAt;

  Map<Integer, Long> stepToResult;
}
