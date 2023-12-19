package com.vimacodes.aoc.day19;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Value;

@Value
class WorkflowSystem {

  private static final int MAX_EVALUATION_STEPS = 10_000;
  private static final String ACCEPT = "A";
  private static final String REJECT = "R";

  Map<String, Workflow> workflows;
  List<MachinePart> parts;

  public static WorkflowSystem parse(String text) {
    String[] parts = text.split("\\r\\n\\r\\n");

    Map<String, Workflow> workflows =
        parts[0].lines().map(Workflow::parse).collect(Collectors.toMap(Workflow::getName, w -> w));
    List<MachinePart> machineParts = parts[1].lines().map(MachinePart::parse).toList();

    System.out.println(workflows);
    System.out.println("-----");
    System.out.println(machineParts);

    return new WorkflowSystem(workflows, machineParts);
  }

  public boolean isAccepted(MachinePart part) {
    return evaluate(part, "in");
  }

  private boolean evaluate(MachinePart part, String startWorkflow) {
    String currentWorkflow = startWorkflow;
    int steps = 0;
    while (++steps < MAX_EVALUATION_STEPS) { // careful!
      Workflow workflow = workflows.get(currentWorkflow);
      String result = workflow.evaluate(part);

      if (result.equals(ACCEPT)) {
        return true;
      }

      if (result.equals(REJECT)) {
        return false;
      }

      currentWorkflow = result;
    }

    return false;
  }

  public long getTotalAcceptedCombos(int to) {
    Range range = new Range(1, to);
    Map<String, Range> ranges =
        Map.of(
            "x", range,
            "m", range,
            "a", range,
            "s", range);

    return getAccepted(new HashMap<>(ranges), "in");
  }

  private long getAccepted(Map<String, Range> ranges, String worfklowName) {
    if (worfklowName.equals(ACCEPT)) {
      return ranges.values().stream().map(Range::length).reduce((a, b) -> a * b).get();
    }

    if (worfklowName.equals(REJECT)) {
      return 0;
    }

    long allAccepts = 0;

    Workflow workflow = workflows.get(worfklowName);
    List<WorkflowRule> rules = workflow.getRules();

    for (var rule : rules) {
      String component = rule.getComponent();
      Range componentRange = ranges.get(component);

      long splitBy = rule.getConstant();
      Result splitComponentRange = splitRange(rule, componentRange, splitBy);

      // send the altered component range for further evaluation (next loop)
      ranges.put(component, splitComponentRange.nonMatching());

      // send the other part in recursion: careful! we need to mutate the range down in recursion,
      // but not in the original map
      var newRanges = new HashMap<>(ranges);
      newRanges.put(component, splitComponentRange.matching());

      allAccepts += getAccepted(newRanges, rule.getDecision());
    }

    // send the remaining to final action
    return allAccepts + getAccepted(ranges, workflow.getFinalAction());
  }

  private static Result splitRange(WorkflowRule rule, Range compRange, long splitBy) {
    Range matching;
    Range nonMatching;

    if (rule.getOperator() == Operator.LESS) {
      matching = new Range(compRange.getFrom(), splitBy - 1);
      nonMatching = new Range(splitBy, compRange.getTo());
    } else { // we only have 2 operators
      matching = new Range(splitBy + 1, compRange.getTo());
      nonMatching = new Range(compRange.getFrom(), splitBy);
    }

    return new Result(matching, nonMatching);
  }

  private record Result(Range matching, Range nonMatching) {}
}
