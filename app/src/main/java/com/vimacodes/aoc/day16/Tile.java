package com.vimacodes.aoc.day16;

import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tile {
  char surface;
  boolean energized;

  public List<Instruction> energize(Instruction inst) {
    energized = true;

    return switch (inst.getDirection()) {
      case UP -> energizeUp(inst);
      case DOWN -> energizeDown(inst);
      case LEFT -> energizeLeft(inst);
      case RIGHT -> energizeRight(inst);
    };
  }

  private List<Instruction> energizeUp(Instruction inst) {
    return switch (surface) {
      case '.', '|' -> List.of(inst.up());
      case '-' -> List.of(inst.left(), inst.right());
      case '\\' -> List.of(inst.left());
      case '/' -> List.of(inst.right());
      default -> Collections.emptyList();
    };
  }

  private List<Instruction> energizeDown(Instruction inst) {
    return switch (surface) {
      case '.', '|' -> List.of(inst.down());
      case '-' -> List.of(inst.left(), inst.right());
      case '\\' -> List.of(inst.right());
      case '/' -> List.of(inst.left());
      default -> Collections.emptyList();
    };
  }

  private List<Instruction> energizeLeft(Instruction inst) {
    return switch (surface) {
      case '.', '-' -> List.of(inst.left());
      case '|' -> List.of(inst.up(), inst.down());
      case '\\' -> List.of(inst.up());
      case '/' -> List.of(inst.down());
      default -> Collections.emptyList();
    };
  }

  private List<Instruction> energizeRight(Instruction inst) {
    return switch (surface) {
      case '.', '-' -> List.of(inst.right());
      case '|' -> List.of(inst.up(), inst.down());
      case '\\' -> List.of(inst.down());
      case '/' -> List.of(inst.up());
      default -> Collections.emptyList();
    };
  }
}
