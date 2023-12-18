package com.vimacodes.aoc.day18;

enum Direction {
  U(-1, 0),
  D(1, 0),
  L(0, -1),
  R(0, 1);

  Direction(int i, int j) {
    shiftRow = i;
    shiftCol = j;
  }

  final int shiftRow;
  final int shiftCol;
}
