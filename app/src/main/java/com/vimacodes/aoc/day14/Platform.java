package com.vimacodes.aoc.day14;

import com.google.common.base.Preconditions;
import java.util.*;

class Platform {

  private static final int MINIMUM_IDLE_CYCLES = 1000;

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
    Set<Long> results = new HashSet<>();

    int counter = 0;
    int idleCycles = 0;
    long result;

    while (idleCycles < MINIMUM_IDLE_CYCLES) {
      runCycle();
      ++counter;

      result = load();
      //      System.out.printf("Load after %d cycles: %d\n", counter, result);
      boolean added = results.add(result);

      if (added) idleCycles = 0;
      else ++idleCycles;
    }

    int start = counter + 1;
    Map<Integer, Long> stepToResult = new TreeMap<>();
    for (int i = 0; i < MINIMUM_IDLE_CYCLES; i++) {
      runCycle();
      ++counter;
      result = load();
      //      if (counter < 1200) System.out.printf("Load after %d cycles: %d\n", counter, result);
      stepToResult.put(counter, result);
    }

    System.out.println(results);
    System.out.println(stepToResult);

    loop = new Loop(start, stepToResult);
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

    if (cycles < loop.getStartAt()) {
      runCycle(cycles);
      return load();
    }

    Map<Integer, Long> results = loop.getStepToResult();
    int diff = cycles - loop.getStartAt();
    int shift = diff % results.size();
    int index = loop.getStartAt() + shift + 1;
    System.out.printf("Diff %d shift %d index %d\n", diff, shift, index);
    return results.get(index);
  }
}
