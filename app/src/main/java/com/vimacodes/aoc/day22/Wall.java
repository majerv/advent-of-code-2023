package com.vimacodes.aoc.day22;

import java.util.*;
import lombok.Value;

@Value
class Wall {

  List<Brick> bricks;

  public static Wall parse(String text) {
    List<Brick> bricks = new ArrayList<>(text.lines().map(Brick::parse).toList());
    return new Wall(bricks);
  }

  public void freeFall() {
    bricks.sort(new LowestPointComparator());

    for (int i = 0; i < bricks.size(); i++) {
      Brick brick = bricks.get(i);
      while (brick.getFirst().getZ() > 1
          && bricks.subList(0, i).stream().noneMatch(b -> b.supports(brick))) {
        brick.sink();
      }
    }

    bricks.sort(new HighestPointComparator());
  }

  public List<Brick> safeToRemove() {
    List<Brick> safeToRemove = new ArrayList<>();
    SupportStats supportStats = buildSupportStats();

    for (int i = 0; i < bricks.size(); i++) {
      Brick brick = bricks.get(i);
      Set<Integer> supported = supportStats.supportedBricksByBrick.get(i);

      if (supported == null
          || supported.stream()
              .noneMatch(s -> supportStats.supporterBricksByBrick.get(s).size() == 1)) {
        System.out.printf("Safe to remove brick id=%d, supported bricks: %s\n", i, supported);
        safeToRemove.add(brick);
      }
    }

    return safeToRemove;
  }

  public long chainReactionSum() {
    SupportStats supportStats = buildSupportStats();

    int all = 0;
    for (int i = 0; i < bricks.size(); i++) {
      Set<Integer> falling = new HashSet<>();
      Deque<Integer> toFall = new LinkedList<>();
      toFall.add(i);

      while (!toFall.isEmpty()) {
        int brickId = toFall.removeFirst();

        if (!falling.contains(brickId)) {
          falling.add(brickId);
          List<Integer> shouldAlsoFall =
              supportStats
                  .supportedBricksByBrick
                  .getOrDefault(brickId, Collections.emptySet())
                  .stream()
                  .filter(s -> !supportStats.supporterBricksByBrick.isEmpty())
                  .filter(s -> falling.containsAll(supportStats.supporterBricksByBrick.get(s)))
                  .toList();

          toFall.addAll(shouldAlsoFall);
        }
      }

      all +=
          falling.size()
              - 1; // remove itself (i.e. was in the set to consider in the chain reaction, but not
                   // counting towards the result
    }
    return all;
  }

  private SupportStats buildSupportStats() {
    Map<Integer, Set<Integer>> supportedBricksByBrick = new HashMap<>();
    Map<Integer, Set<Integer>> supporterBricksByBrick = new HashMap<>();

    for (int i = 0; i < bricks.size(); i++) {
      Brick brick = bricks.get(i);

      for (int j = i + 1; j < bricks.size(); j++) {
        Brick other = bricks.get(j);
        if (brick.supports(other)) {
          supportedBricksByBrick.putIfAbsent(i, new HashSet<>());
          supportedBricksByBrick.get(i).add(j);

          supporterBricksByBrick.putIfAbsent(j, new HashSet<>());
          supporterBricksByBrick.get(j).add(i);
        }
      }
    }

    return new SupportStats(supportedBricksByBrick, supporterBricksByBrick);
  }

  private record SupportStats(
      Map<Integer, Set<Integer>> supportedBricksByBrick,
      Map<Integer, Set<Integer>> supporterBricksByBrick) {}
}
