package com.vimacodes.aoc.day15;

class HASHAlgorithm {
  public static long hash(String input) {
    long result = 0;

    for (int i = 0; i < input.length(); i++) {
      result += input.codePointAt(i);
      result *= 17;
      result %= 256;
    }

    return result;
  }
}
