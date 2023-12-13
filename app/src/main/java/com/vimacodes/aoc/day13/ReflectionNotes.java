package com.vimacodes.aoc.day13;

import java.util.List;
import lombok.Value;

@Value
class ReflectionNotes {

  int rows;
  int cols;
  Character[][] matrix;

  public static ReflectionNotes parse(String text) {
    List<String> lines = text.lines().toList();
    int rows = lines.size();
    int cols = lines.get(0).length();

    Character[][] matrix = new Character[rows][cols];
    String line;
    for (int i = 0; i < rows; i++) {
      line = lines.get(i);
      for (int j = 0; j < cols; j++) {
        char c = line.charAt(j);
        matrix[i][j] = c;
      }
    }

    return new ReflectionNotes(rows, cols, matrix);
  }

  public long summarize() {
    return summarizeWithSmudges(0);
  }

  public long summarizeWithSmudges(final int expectedSmudges) {
    return summarizeVertical(expectedSmudges) + summarizeHorizontal(expectedSmudges);
  }

  private long summarizeHorizontal(final int expectedSmudges) {
    int result = 0;

    for (int row = 0; row < rows - 1; row++) { // for each row [0, size-1]
      int smudges = 0;
      int above, below;

      for (int diff = 0; diff < cols; diff++) {
        above = row - diff;
        below = row + diff + 1;
        if (0 <= above && below < rows && above < below) {
          for (int j = 0; j < cols; j++) {
            boolean error = matrix[above][j] != matrix[below][j];
            if (error) ++smudges;
          }
        }
      }
      if (smudges == expectedSmudges) result += 100 * (row + 1);
    }

    return result;
  }

  private long summarizeVertical(final int expectedSmudges) {
    int result = 0;

    for (int col = 0; col < cols - 1; col++) { // for each column [0, size-1]
      int smudges = 0;
      int left, right;

      for (int diff = 0; diff < cols; diff++) {
        left = col - diff;
        right = col + diff + 1;
        if (0 <= left && right < cols && left < right) {
          for (int i = 0; i < rows; i++) {
            boolean error = matrix[i][left] != matrix[i][right];
            if (error) ++smudges;
          }
        }
      }
      if (smudges == expectedSmudges) result += col + 1;
    }

    return result;
  }
}
