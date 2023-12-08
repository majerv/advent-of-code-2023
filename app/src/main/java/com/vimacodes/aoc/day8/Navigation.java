package com.vimacodes.aoc.day8;

import java.util.List;
import lombok.ToString;

@ToString
class Navigation {

  List<Turn> turns;
  int currentTurn = 0;

  public Navigation(List<Turn> turns) {
    this.turns = turns;
  }

  public Turn next() {
    int index = currentTurn++ % turns.size();
    return turns.get(index);
  }
}
