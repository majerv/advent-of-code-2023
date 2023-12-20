package com.vimacodes.aoc.day20;

import com.vimacodes.aoc.day20.ModuleConfiguration.Result;

class Day20Exercise1 {
  public long solve(final String text) {
    ModuleConfiguration moduleConfiguration = ModuleConfiguration.parse(text);
    System.out.println(moduleConfiguration);

    Result result = moduleConfiguration.pushButton(1000);
    System.out.println(result);
    return result.lowPulses() * result.highPulses();
  }
}
