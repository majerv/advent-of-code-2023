package com.vimacodes.aoc.day22;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/** Represented with their top-left-closest corner in the 3D space. */
@Data
@AllArgsConstructor
class Cube {
  int x;
  int y;
  int z;

  public static Cube parse(String representation) {
    List<Integer> coordinates =
        Arrays.stream(representation.trim().split(",")).map(Integer::parseInt).toList();

    Preconditions.checkArgument(coordinates.size() == 3, "A cube must have 3 coordinates");

    int x = coordinates.get(0);
    int y = coordinates.get(1);
    int z = coordinates.get(2);

    return new Cube(x, y, z);
  }
}
