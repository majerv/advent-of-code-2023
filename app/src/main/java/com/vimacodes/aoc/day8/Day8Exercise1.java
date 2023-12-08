package com.vimacodes.aoc.day8;

class Day8Exercise1 {
  public long solve(final String text) {
    Network network = Network.parse(text);
    System.out.println(network);

    Node start = new Node("AAA");
    Node end = new Node("ZZZ");

    Node current = start;
    Navigation navigation = network.navigate();
    int steps = 0;
    while (!current.equals(end)) {
      Turn turn = navigation.next();
      Node arrived = network.getNode(current, turn);
      System.out.printf("At %s turning %s -> %s\n", current, turn, arrived);
      current = arrived;
      ++steps;
    }

    return steps;
  }
}
