package com.vimacodes.aoc.day24;

import lombok.Value;

@Value
class Area {
  Point2d bottomLeft;
  Point2d topRight;

  public boolean inside(double x, double y) {
    return bottomLeft.getX() <= x
        && x <= topRight.getX()
        && bottomLeft.getY() <= y
        && y <= topRight.getY();
  }

  public boolean inside(Point2d point) {
    return inside(point.getX(), point.getY());
  }
}
