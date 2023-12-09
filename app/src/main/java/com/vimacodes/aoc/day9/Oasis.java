package com.vimacodes.aoc.day9;

import java.util.List;

record Oasis(List<ValueHistory> histories) {

  public static Oasis parse(String text) {
    List<ValueHistory> histories = text.lines().map(ValueHistory::parse).toList();
    return new Oasis(histories);
  }
}
