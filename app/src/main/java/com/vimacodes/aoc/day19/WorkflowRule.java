package com.vimacodes.aoc.day19;

import lombok.Value;

@Value
class WorkflowRule {

  String component;
  //  Predicate<Long> predicate;
  Operator operator;
  Long constant;
  String decision;

  public static WorkflowRule parse(String str) {
    String component = str.substring(0, 1);
    Operator op = Operator.fromSymbol(str.substring(1, 2));
    int colonIndex = str.indexOf(":");

    return new WorkflowRule(
        component, op, Long.parseLong(str.substring(2, colonIndex)), str.substring(colonIndex + 1));
  }

  public boolean matches(MachinePart part) {
    long value = part.getComponent(component);

    if (operator == Operator.LESS) return value < constant;
    else if (operator == Operator.GREATER) return value > constant;

    return false;
  }
}
