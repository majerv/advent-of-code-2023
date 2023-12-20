package com.vimacodes.aoc.day20;

import com.vimacodes.aoc.utils.MathUtils;
import java.util.List;

class Day20Exercise2 {
  public long solve(final String text) {
    ModuleConfiguration moduleConfiguration = ModuleConfiguration.parse(text);
    System.out.println(moduleConfiguration);

    String destination = "rx";
    // the input is forged... simplifying life: finding inputs up to 2 level from destination
    System.out.println(moduleConfiguration.findInputs(destination));

    List<ModuleConfiguration.SourceDest> sourceDestinations =
        moduleConfiguration.findInputs(destination).stream()
            .flatMap(sd -> moduleConfiguration.findInputs(sd.source()).stream())
            .toList();

    System.out.println(sourceDestinations);
    List<Long> numberOfPressesToEachDestination =
        sourceDestinations.stream()
            .map(sd -> moduleConfiguration.getPressesTilHighPulse(sd.source(), sd.destination()))
            .toList();

    return MathUtils.lcm(numberOfPressesToEachDestination);
  }
}
