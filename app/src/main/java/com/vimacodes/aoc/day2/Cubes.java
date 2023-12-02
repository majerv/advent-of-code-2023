package com.vimacodes.aoc.day2;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
class Cubes {

  int red, green, blue;

  public boolean isValid() {
    return red >= 0 && green >= 0 && blue >= 0;
  }

  public Cubes subtract(final Cubes other) {
    return Cubes.builder()
        .red(red - other.red)
        .green(green - other.green)
        .blue(blue - other.blue)
        .build();
  }

  private void add(Setup setup) {
    final int count = setup.count();

    switch (setup.color()) {
      case RED -> red += count;
      case GREEN -> green += count;
      case BLUE -> blue += count;
    }
  }

  /*
   * e.g.
   * 3 blue, 4 red OR
   * 1 red, 2 green, 6 blue OR just
   * 2 green
   */
  public static Cubes parse(String line) {
    if (!line.contains(",")) {
      return Cubes.from(List.of(Setup.parse(line)));
    }

    List<Setup> setups = Arrays.stream(line.split(",")).map(Setup::parse).toList();
    return Cubes.from(setups);
  }

  private static Cubes from(Collection<Setup> setups) {
    Cubes cubes = new Cubes(0, 0, 0);
    setups.forEach(cubes::add);
    return cubes;
  }

  public long power() {
    return (long) red * green * blue;
  }

  public Cubes commonGreatest(Cubes other) {
    return Cubes.builder()
        .red(Integer.max(red, other.red))
        .green(Integer.max(green, other.green))
        .blue(Integer.max(blue, other.blue))
        .build();
  }
}
