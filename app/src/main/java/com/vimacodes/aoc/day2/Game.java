package com.vimacodes.aoc.day2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class Game {
  int id;
  List<Cubes> rounds;

  public boolean isValidConfig(Cubes config) {
    return rounds.stream()
        .map(config::subtract)
        .peek(System.out::println)
        .filter(Predicate.not(Cubes::isValid))
        .findFirst()
        .isEmpty();
  }

  // e.g. "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"

  public static Game parse(@NonNull final String gameLine) {
    return Game.builder().id(parseId(gameLine)).rounds(parseRounds(gameLine)).build();
  }

  // e.g. "Game 1: ..."

  private static int parseId(String gameLine) {
    return Integer.parseInt(gameLine.split(":")[0].split("\\s+")[1]);
  }

  // e.g. "...: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"

  private static List<Cubes> parseRounds(String gameLine) {
    String[] rounds = gameLine.split(":")[1].split(";");

    return Arrays.stream(rounds).map(Cubes::parse).toList();
  }

  public Cubes minimalConfig() {
    Cubes minimal = new Cubes(0, 0, 0);
    for (Cubes r : rounds) {
      minimal = minimal.commonGreatest(r);
    }
    return minimal;
  }
}
