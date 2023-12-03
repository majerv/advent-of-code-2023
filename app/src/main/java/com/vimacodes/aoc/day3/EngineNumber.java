package com.vimacodes.aoc.day3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
class EngineNumber {

  int number;
  Position start;
  Position end;

  // FIXME: refactor heavily :)
  public Collection<Position> neighbourPositions() {
    List<Position> all = new ArrayList<>();

    List<Position> upper =
        IntStream.range(start.col() - 1, end.col() + 2)
            .mapToObj(i -> new Position(start.row() - 1, i))
            .toList();

    List<Position> lower =
        IntStream.range(start.col() - 1, end.col() + 2)
            .mapToObj(i -> new Position(start.row() + 1, i))
            .toList();

    all.addAll(upper);
    all.addAll(lower);
    all.add(new Position(start.row(), start.col() - 1));
    all.add(new Position(start.row(), end.col() + 1));

    //    System.out.println("Neighbour positions: " + all);

    return all;
  }
}
