package com.vimacodes.aoc.day19;

import lombok.Value;

@Value
class Range {
  long from; // inclusive
  long to; // inclusive

  long length() {
    return to - from + 1; // e.g. [1-10] -> 10 numbers
  }
}
