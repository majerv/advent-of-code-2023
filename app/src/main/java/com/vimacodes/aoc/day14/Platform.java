package com.vimacodes.aoc.day14;

import java.util.List;
import lombok.Value;

@Value
class Platform {

  int rows;
  int cols;
  Character[][] platform;

  public static Platform parse(String text) {
    List<String> lines = text.lines().toList();
    int rows = lines.size();
    int cols = lines.get(0).length();

    Character[][] platform = new Character[rows][cols];
    String line;
    for (int i = 0; i < rows; i++) {
      line = lines.get(i);
      for (int j = 0; j < cols; j++) {
        char c = line.charAt(j);
        platform[i][j] = c;
      }
    }

    return new Platform(rows, cols, platform);
  }

  public Platform tiltNorth() {
    for (int i = 1; i < rows; i++) { // from the 2nd row only
      for (int j = 0; j < cols; j++) {
        if (platform[i][j] == 'O') {
          int k = i - 1;
          char swap;
          while (k >= 0 && platform[k][j] == '.') {
            swap = platform[k][j];
            platform[k][j] = platform[k + 1][j];
            platform[k + 1][j] = swap;
            --k;
          }
        }
      }
    }

    return this;
  }

  public long load() {
    int load = 0;
    for (int i = 0; i < rows; i++) {
      int rocks = 0;
      for (int j = 0; j < cols; j++) {
        if (platform[i][j] == 'O') ++rocks;
      }
      load += (rows - i) * rocks;
    }
    return load;
  }

  public String prettyPrint() {
    StringBuilder str = new StringBuilder();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        char c = platform[i][j];
        str.append(c);
      }
      str.append("\n");
    }

    return str.toString();
  }
}
