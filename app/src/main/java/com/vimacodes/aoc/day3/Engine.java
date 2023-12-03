package com.vimacodes.aoc.day3;

import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;

class Engine {

  private char[][] scheme;

  public static Engine parse(@NonNull final String engineScheme) {
    return new Engine();
  }

  public List<EngineNumber> numbers() {
    return new ArrayList<>();
  }

  public boolean isAdjacentToSymbol(EngineNumber number) {
    return false;
  }
}
