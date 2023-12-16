package com.vimacodes.aoc.day14;

import com.google.common.base.Preconditions;
import java.util.*;

class Platform {

  private final int rows;
  private final int cols;
  private final Character[][] platform;

  private Loop loop;

  public Platform(Character[][] platform) {
    this.platform = platform;

    rows = platform.length;
    Preconditions.checkArgument(rows > 0);
    cols = platform[0].length;
  }

  public void findLoop() {
    Map<String, Integer> prints = new HashMap<>();

    int counter = 0;
    boolean cycleFound = false;

    String print = "";
    while (!cycleFound) {
      runCycle();
      ++counter;

      print = prettyPrint();
      cycleFound = prints.containsKey(print);
      if (!cycleFound) prints.put(print, counter);
    }

    int start = prints.get(print);
    loop = new Loop(start, counter - start);
    System.out.println("Loop found: " + loop);
  }

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

    return new Platform(platform);
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

  public void runCycle(int rounds) {
    for (int i = 0; i < rounds; i++) {
      runCycle();
    }
  }

  public void runCycle() {
    tiltNorth();
    tiltWest();
    tiltSouth();
    tiltEast();
  }

  public void tiltWest() {
    for (int i = 0; i < rows; i++) {
      for (int j = 1; j < cols; j++) {
        if (platform[i][j] == 'O') {
          int k = j - 1;
          char swap;
          while (k >= 0 && platform[i][k] == '.') {
            swap = platform[i][k];
            platform[i][k] = platform[i][k + 1];
            platform[i][k + 1] = swap;
            --k;
          }
        }
      }
    }
  }

  public void tiltSouth() {
    for (int i = 1; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (platform[rows - i - 1][j] == 'O') {
          int k = rows - i;
          char swap;
          while (k < rows && platform[k][j] == '.') {
            swap = platform[k][j];
            platform[k][j] = platform[k - 1][j];
            platform[k - 1][j] = swap;
            ++k;
          }
        }
      }
    }
  }

  public void tiltEast() {
    for (int i = 0; i < rows; i++) {
      for (int j = 1; j < cols; j++) {
        if (platform[i][cols - j - 1] == 'O') {
          int k = cols - j;
          char swap;
          while (k < cols && platform[i][k] == '.') {
            swap = platform[i][k];
            platform[i][k] = platform[i][k - 1];
            platform[i][k - 1] = swap;
            ++k;
          }
        }
      }
    }
  }

  public long loadAfterCycles(int cycles) {
    if (loop == null) findLoop();

    Preconditions.checkArgument(loop.getStartAt() < cycles);

    int diff = cycles - loop.getStartAt();
    int shift = diff % loop.getLength();

    runCycle(shift);
    return load();
  }
}
