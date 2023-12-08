package com.vimacodes.aoc.day8;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import lombok.Value;

@Value
class Network {

  List<Turn> turns;
  Map<String, Map.Entry<String, String>> map;

  public static Network parse(String text) {
    List<String> lines = text.lines().toList();

    List<Turn> turns =
        lines.get(0).chars().mapToObj(ch -> Turn.valueOf(String.valueOf((char) ch))).toList();

    Map<String, Map.Entry<String, String>> map = buildMap(lines.subList(2, lines.size()));

    return new Network(turns, map);
  }

  public Navigation navigate() {
    return new Navigation(turns);
  }

  public long getStepsToZ(@NonNull String start) {
    Navigation navigation = navigate();
    long steps = 0;
    String current = start;
    while (!current.endsWith("Z")) {
      Turn turn = navigation.next();
      current = getNode(current, turn);
      ++steps;
    }

    return steps;
  }

  public Node getNode(Node current, Turn turn) {
    Map.Entry<String, String> next = map.get(current.getLabel());
    String label = turn == Turn.L ? next.getKey() : next.getValue();
    return new Node(label);
  }

  public String getNode(String current, Turn turn) {
    Map.Entry<String, String> next = map.get(current);
    return turn == Turn.L ? next.getKey() : next.getValue();
  }

  private static Map<String, Map.Entry<String, String>> buildMap(List<String> lines) {
    Map<String, Map.Entry<String, String>> map = new HashMap<>();

    String key, left, right;
    for (String line : lines) {
      String[] parts = line.split("=");
      key = parts[0].strip();
      String[] turns = parts[1].strip().replace("(", "").replace(")", "").split(",");
      left = turns[0].strip();
      right = turns[1].strip();
      map.put(key, new AbstractMap.SimpleImmutableEntry<>(left, right));
    }

    return map;
  }
}
