package com.vimacodes.aoc.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringUtils {

  private StringUtils() {}

  public static List<Integer> findAllIndicesOf(final String str, final char toFind) {
    return IntStream.range(0, str.length())
        .filter(i -> str.charAt(i) == toFind)
        .boxed()
        .collect(Collectors.toList());
  }
}
