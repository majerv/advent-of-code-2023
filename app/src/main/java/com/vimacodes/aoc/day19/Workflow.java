package com.vimacodes.aoc.day19;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.Value;

@Value
class Workflow {

  String name;
  List<WorkflowRule> rules;
  String finalAction;

  public String evaluate(MachinePart part) {
    Optional<WorkflowRule> firstMatch = rules.stream().filter(r -> r.matches(part)).findFirst();

    if(firstMatch.isPresent()) {
      return firstMatch.get().getDecision();
    }

    return finalAction;
  }

  public static Workflow parse(String line) {
    int indexOfOpenBrace = line.indexOf("{");
    String name = line.substring(0, indexOfOpenBrace);

    String rules =
        line.substring(indexOfOpenBrace + 1, line.length() - 1); // i.e. removing the closing '}'

    String[] parts = rules.split(",");

    List<WorkflowRule> workflowRules =
        Arrays.stream(Arrays.copyOfRange(parts, 0, parts.length - 1))
            .map(WorkflowRule::parse)
            .toList();

    String finalAction = parts[parts.length - 1];

    return new Workflow(name, workflowRules, finalAction);
  }
}
