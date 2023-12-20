package com.vimacodes.aoc.day8;

import com.vimacodes.aoc.utils.MathUtils;
import java.util.ArrayList;
import java.util.List;

class Day8Exercise2 {
  public long solve(final String text) {
    Network network = Network.parse(text);
    System.out.println(network);

    List<String> startNodes =
        new ArrayList<>(network.getMap().keySet().stream().filter(k -> k.endsWith("A")).toList());

    System.out.println("Start nodes: " + startNodes);

    List<Long> numberOfStepsToZFromEachNode =
        startNodes.stream().map(network::getStepsToZ).toList();

    return MathUtils.lcm(numberOfStepsToZFromEachNode);
  }

}
