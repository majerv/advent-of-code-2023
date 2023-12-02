package com.vimacodes.aoc.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GamesTest {

  @ParameterizedTest
  @CsvSource(
      ignoreLeadingAndTrailingWhitespace = false,
      delimiterString = "|",
      value = {
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green|Game(id=1, rounds=[Cubes(red=4, green=0, blue=3), Cubes(red=1, green=2, blue=6), Cubes(red=0, green=2, blue=0)])"
      })
  void parseGames(String line, String expectedToString) {
    Game game = Game.parse(line);
    Assertions.assertEquals(expectedToString, game.toString());
  }
}
