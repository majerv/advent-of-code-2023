package com.vimacodes.aoc.day17;

import java.util.*;
import java.util.function.Function;
import lombok.Value;

@Value
public class CityMap {

  int rows;
  int cols;
  List<List<Integer>> cityBlocks;

  public static CityMap parse(String text) {
    List<String> lines = text.lines().toList();
    int rows = lines.size();
    int cols = lines.get(0).length();

    var blocks = lines.stream().map(CityMap::toBlocksInARow).toList();
    return new CityMap(rows, cols, blocks);
  }

  private static List<Integer> toBlocksInARow(String line) {
    return line.chars().mapToObj(Character::getNumericValue).toList();
  }

  public int minimalHeatLossPath() {
    return minimalHeatLossPath(Instruction::nextSteps, 1, Instruction.COUNTER_LIMIT);
  }

  public int minimalHeatLossPathWithUltraCrucibles() {
    return minimalHeatLossPath(
        Instruction::nextStepsUltra, Instruction.ULTRA_COUNTER_MIN, Instruction.ULTRA_COUNTER_MAX);
  }

  public int minimalHeatLossPath(
      Function<Instruction, List<Instruction>> nextInstructionsProvider,
      int minSteps,
      int maxSteps) {
    PriorityQueue<Move> moves = new PriorityQueue<>(new Move.HeatLossComparator());
    Set<Instruction> completedInstructions = new HashSet<>();

    // bottom right
    Position end = new Position(rows - 1, cols - 1);

    // from top left
    moves.add(new Move(0, new Instruction(0, 0, null, 0)));

    int minimal = 0;
    Move move;
    while (!moves.isEmpty()) {
      move = moves.remove();

      Instruction moveInstruction = move.getInstruction();
      if (moveInstruction.getPosition().equals(end) && moveInstruction.getCounter() >= minSteps) {
        return move.getHeatLoss();
      }

      if (!completedInstructions.contains(moveInstruction)
          && moveInstruction.isValid(rows, cols, maxSteps)) {
        completedInstructions.add(moveInstruction);

        List<Instruction> nextSteps = nextInstructionsProvider.apply(moveInstruction);
        for (Instruction next : nextSteps) {
          if (next.isValid(rows, cols, maxSteps)) {
            int hl = move.getHeatLoss() + weight(next.getPosition());
            Move newMove = new Move(hl, next);
            if (!completedInstructions.contains(newMove.getInstruction())) {
              moves.add(newMove);
            }
          }
        }
      }
    }

    return minimal;
  }

  public int minimalHeatLossPathRecursive() {
    Instruction start = new Instruction(0, 1, Direction.RIGHT, 1);
    Instruction start2 = new Instruction(1, 0, Direction.DOWN, 1);

    Instruction goal = new Instruction(rows - 1, cols - 1, Direction.DOWN, 1);

    Set<Position> visited = new LinkedHashSet<>();
    visited.add(new Position(0, 0));

    Map<Position, Integer> memo = new HashMap<>();

    return Math.min(
        minimal(start, goal, visited, memo).orElse(Integer.MAX_VALUE),
        minimal(start2, goal, visited, memo).orElse(Integer.MAX_VALUE));
  }

  private Optional<Integer> minimal(
      Instruction from, Instruction to, Set<Position> visited, Map<Position, Integer> memo) {
    Position fromPosition = from.getPosition();
    if (memo.containsKey(fromPosition)) {
      return Optional.of(memo.get(fromPosition));
    }

    visited.add(fromPosition);

    if (fromPosition.equals(to.getPosition())) {
      int result = weight(fromPosition);
      memoize(from, memo, result);
      return Optional.of(result);
    }

    Set<Position> newVisited = new LinkedHashSet<>(visited);

    // recursion
    List<Instruction> nextSteps = from.nextSteps();
    //    System.out.println("Next steps: " + nextSteps);

    List<Instruction> validSteps =
        nextSteps.stream()
            .filter(s -> !newVisited.contains(s.getPosition()))
            .filter(s -> s.isValid(rows, cols, Instruction.COUNTER_LIMIT))
            .toList();
    //    System.out.println("Valid next steps: " + validSteps);

    var min =
        validSteps.stream()
            .map(s -> minimal(s, to, newVisited, memo))
            .filter(Optional::isPresent)
            .mapToInt(Optional::get)
            .min();
    //    Preconditions.checkArgument(min.isPresent());

    if (min.isPresent()) {
      int result = weight(fromPosition) + min.getAsInt();
      memoize(from, memo, result);
      //      System.out.printf(
      //          "Found minimum from %s to %s: %s\n", fromPosition, to.getPosition(), result);
      return Optional.of(result);
    } else {
      return Optional.empty();
    }
  }

  private static void memoize(Instruction from, Map<Position, Integer> memo, int result) {
    Position fromPosition = from.getPosition();
    System.out.printf("Memoizing: %s -> %d\n", fromPosition, result);
    memo.put(fromPosition, result);
  }

  private int weight(Position from) {
    return cityBlocks.get(from.getRow()).get(from.getCol());
  }

  public void prettyPrint() {
    StringBuilder str = new StringBuilder();

    for (var blocksRow : cityBlocks) {
      for (var block : blocksRow) {
        str.append(block);
        str.append(",");
      }
      str.append("\n");
    }

    System.out.println(str);
  }
}
