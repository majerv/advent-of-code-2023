package com.vimacodes.aoc.day19;

class Day19Exercise2 {
  public long solve(final String text) {
    WorkflowSystem workflowSystem = WorkflowSystem.parse(text);

    return workflowSystem.getTotalAcceptedCombos(4000);
  }
}
