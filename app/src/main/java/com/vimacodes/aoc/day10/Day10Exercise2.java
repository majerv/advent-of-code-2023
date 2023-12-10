package com.vimacodes.aoc.day10;

import java.util.List;

class Day10Exercise2 {

  public long solve(final String text, Character cheat) {
    Maze maze = Maze.parse(text);
    System.out.println(maze.toPrettyString());

    Position start = maze.findStart();
    List<Position> loop = maze.findLoop(start);

    return maze.findPositionsEnclosedByWithRayCast(loop, cheat);
  }
}
