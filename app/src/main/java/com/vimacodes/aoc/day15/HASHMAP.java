package com.vimacodes.aoc.day15;

import java.util.*;
import lombok.Value;

@Value
class HASHMAP {

  List<Step> steps;

  public static HASHMAP parse(String text) {
    List<Step> steps = Arrays.stream(text.split(",")).map(Step::parse).toList();
    return new HASHMAP(steps);
  }

  private Map<Integer, Map<String, ValueHolder>> processLabels() {
    Map<Integer, Map<String, ValueHolder>> boxes = new HashMap<>();

    for (Step step : steps) {
      String label = step.getLabel();
      int box = HASHAlgorithm.hash(label);
      if (step.isRemove()) {
        if (boxes.containsKey(box)) boxes.get(box).remove(label);
      } else {
        Integer focalLength = step.getFocalLength();
        if (boxes.containsKey(box)) {
          var actualBox = boxes.get(box);
          if (actualBox.containsKey(label)) {
            actualBox.get(label).setValue(focalLength);
          } else {
            actualBox.put(label, new ValueHolder(focalLength));
          }
        } else {
          LinkedHashMap<String, ValueHolder> newMap = new LinkedHashMap<>();
          newMap.put(label, new ValueHolder(focalLength));
          boxes.put(box, newMap);
        }
      }
    }

    return boxes;
  }

  public long totalFocusingPower() {
    var boxes = processLabels();
    int total = 0;

    for (var box : boxes.entrySet()) {
      int boxNum = box.getKey() + 1;
      int slot = 0;
      for (var lens : box.getValue().entrySet()) {
        ++slot;
        total += boxNum * slot * lens.getValue().getValue();
      }
    }

    return total;
  }
}
