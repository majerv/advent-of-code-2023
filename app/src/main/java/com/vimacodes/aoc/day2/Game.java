package com.vimacodes.aoc.day2;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Game {
  int id;
  List<Cubes> rounds;

  // e.g. "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
  public static Game parse(final String gameLine) {
    return Game.builder().id(parseId(gameLine)).rounds(parseRounds(gameLine)).build();
  }

  private static int parseId(String gameLine) {
    return 0;
  }

  private static List<Cubes> parseRounds(String gameLine) {
    return null;
  }
}
