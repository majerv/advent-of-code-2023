package com.vimacodes.aoc.day19;

class Day19Exercise1 {
  public long solve(final String text) {
    WorkflowSystem workflowSystem = WorkflowSystem.parse(text);

    return workflowSystem.getParts().stream()
        .filter(workflowSystem::isAccepted)
        .mapToLong(MachinePart::getOverallRating)
        .sum();
  }
}
