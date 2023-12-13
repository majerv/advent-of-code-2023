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
    return summarizeVertical() + summarizeHorizontal();
  }

  private long summarizeHorizontal() {
    int result = 0;

    for (int row = 0; row < rows - 1; row++) { // for each row [1, size-1]
      boolean reflection = true;
      int above, below;

      for (int diff = 0; diff < cols; diff++) {
        above = row - diff;
        below = row + diff + 1;
        if (0 <= above && below < rows && above < below) {
          for (int j = 0; j < cols; j++) {
            reflection &= matrix[above][j] == matrix[below][j];
          }
        }
      }
      if (reflection) result += 100 * (row + 1);
    }

    return result;
  }

  private long summarizeVertical() {
    int result = 0;

    for (int col = 0; col < cols - 1; col++) { // for each column [0, size-1]
      boolean reflection = true;
      int left, right;

      for (int diff = 0; diff < cols; diff++) {
        left = col - diff;
        right = col + diff + 1;
        if (0 <= left && right < cols && left < right) {
          for (int i = 0; i < rows; i++) {
            reflection &= matrix[i][left] == matrix[i][right];
          }
        }
      }
      if (reflection) result += col + 1;
    }

    return result;
  }
}
