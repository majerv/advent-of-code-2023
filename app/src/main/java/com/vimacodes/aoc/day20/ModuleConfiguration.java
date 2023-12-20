package com.vimacodes.aoc.day20;

import lombok.Value;

@Value
class ModuleConfiguration {
  public static ModuleConfiguration parse(String text) {
    return new ModuleConfiguration();
  }

  public Result pushButton(int times) {
    return new Result(0, 0);
  }

  public record Result(long lowPulses, long highPulses) {}
}
