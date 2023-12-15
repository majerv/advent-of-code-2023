package com.vimacodes.aoc.day15;

import lombok.Value;

@Value
class Step {
  String label;
  Character operation;
  Integer focalLength;

  public static Step parse(String instruction) {
    char operation = instruction.contains("=") ? '=' : '-';
    int operationIndex = instruction.indexOf(operation);

    String label = instruction.substring(0, operationIndex);
    Integer focalLength =
        operation == '-' ? null : Integer.parseInt(instruction.substring(operationIndex + 1));

    return new Step(label, operation, focalLength);
  }

  public boolean isRemove() {
    return operation == '-';
  }
}
