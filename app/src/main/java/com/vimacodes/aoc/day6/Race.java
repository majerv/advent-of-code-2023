package com.vimacodes.aoc.day6;

import lombok.Value;

@Value
class Race {

  long duration;
  long record;

  public long numberOfWaysToBeatRecord() {
    int counter = 0;

    for (int i = 1; i < duration - 1; i++) {
      long distance = (duration - i) * i;
      if (distance > record) ++counter;
    }

    return counter;
  }
}
