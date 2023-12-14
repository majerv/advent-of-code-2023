package com.vimacodes.aoc.day14;

class Day14Exercise2 {
  public long solve(final String text) {
    Platform platform = Platform.parse(text);
    System.out.println(platform.prettyPrint());

    //    if (true) {
    //      platform.findLoop();
    //      return platform.load();
    //    } else {
    //      for (int i = 0; i < 1_000; i++) {
    //        platform.runCycle();
    //        System.out.printf("Load after %d cycles: %d\n", i + 1, platform.load());
    //      }
    //      return platform.load();
    //    }
    return platform.loadAfterCycles(1_000_000_000);
  }
}
