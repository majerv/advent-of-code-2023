package com.vimacodes.aoc.day22;

import lombok.Value;

@Value
class Brick {

  Cube first;
  Cube last;
//  Set<Brick> supporters;
//  Set<Brick> supported;

  public static Brick parse(String line) {
    String[] parts = line.split("~");
    return new Brick(Cube.parse(parts[0]), Cube.parse(parts[1])/*, new HashSet<>(), new HashSet<>()*/);
  }

  public Side top() {
    if (first.getZ() == last.getZ()) {
      return new Side(first.x, first.y, last.z, last.x + 1, last.y + 1);
    } else { // vertical
      return new Side(last.x, last.y, last.z, last.x + 1, last.y + 1);
    }
  }

  public Side bottom() {
    if (first.getZ() == last.getZ()) {
      return new Side(first.x, first.y, first.z - 1, last.x + 1, last.y + 1);
    } else { // vertical
      return new Side(first.x, first.y, first.z - 1, first.x + 1, first.y + 1);
    }
  }

  public boolean supports(Brick brick) {
    return overlap(this.top(), brick.bottom());
  }

  private boolean overlap(Side top, Side bottom) {
    return top.getZ() == bottom.getZ()
        && Math.min(top.getX2(), bottom.getX2()) > Math.max(top.getX(), bottom.getX())
        && Math.min(top.getY2(), bottom.getY2()) > Math.max(top.getY(), bottom.getY());
  }

  public void sink() {
    first.setZ(first.getZ() - 1);
    last.setZ(last.getZ() - 1);
  }

//  public void registerSupporter(Brick supporter) {
//    supporters.add(supporter);
//    supporter.registerSupported(this);
//  }
//
//  private void registerSupported(Brick brick) {
//    supported.add(brick);
//  }
}
