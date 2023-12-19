package com.vimacodes.aoc.day19;

import lombok.Getter;

@Getter
enum Operator {
  LESS("<"),
  GREATER(">");

  Operator(String symbol) {
    this.symbol = symbol;
  }

  private final String symbol;

  public static Operator fromSymbol(String symbol) {
    return symbol.equals("<") ? LESS : GREATER;
  }
}
