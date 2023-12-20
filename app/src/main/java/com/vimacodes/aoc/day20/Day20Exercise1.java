package com.vimacodes.aoc.day20;

import com.vimacodes.aoc.day20.ModuleConfiguration.Result;

class Day20Exercise1 {
  public long solve(final String text) {
    ModuleConfiguration moduleConfiguration = ModuleConfiguration.parse(text);

    Result result = moduleConfiguration.pushButton(1000);
    return result.lowPulses() * result.highPulses();
  }
}
