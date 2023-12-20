package com.vimacodes.aoc.day24;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Value;

@Value
class Hails {

  List<HailStone> stones;

  public static Hails parse(String text) {
    List<HailStone> stones = text.lines().map(HailStone::parse).toList();
    return new Hails(stones);
  }

  public List<HailStone> stones() {
    return stones;
  }

  public HailStone calculateRockThatHitsAll() {
    double xr = 0, yr = 0, zr = 0, dxr = 0, dyr = 0, dzr = 0;

    List<String> equations = generateEquations();

    // then copy it into https://live.sympy.org/ ... tried java libraries, but it's not worth it
    // see images in the /img folder
    System.out.println("Equations to solve:");
    System.out.println(equations.stream().collect(Collectors.joining(",", "[", "]")));

    return new HailStone(new Point3d(xr, yr, zr), new Vector(dxr, dyr, dzr));
  }

  private List<String> generateEquations() {
    List<String> equations = new ArrayList<>();

    for (HailStone stone : stones) {

      Point3d pos = stone.getPosition();
      Vector vec = stone.getFallingVector();
      String equationXY =
          String.format(
              "(%s-%f) * (%f-%s) - (%s-%f) * (%f-%s)",
              "xr", pos.getX(), vec.getY(), "dyr", "yr", pos.getY(), vec.getX(), "dxr");

      String equationXZ =
          String.format(
              "(%s-%f) * (%f-%s) - (%s-%f) * (%f-%s)",
              "xr", pos.getX(), vec.getZ(), "dzr", "zr", pos.getZ(), vec.getX(), "dxr");

      equations.add(equationXY);
      equations.add(equationXZ);
    }
    return equations;
  }
}
