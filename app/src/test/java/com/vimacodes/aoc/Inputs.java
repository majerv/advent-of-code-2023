package com.vimacodes.aoc;

import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Inputs {
  private Inputs() {}

  public static String resourceToString(String resourceName) {
    URL url = Resources.getResource(resourceName);

    try {
      return Resources.toString(url, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException("Could not open file: " + resourceName, e);
    }
  }
}
