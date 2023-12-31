package com.vimacodes.aoc.day24;

import lombok.Value;

@Value
class HailStone {

  // could be 3d too
  Point3d position;
  // from the origo
  Vector fallingVector;

  public static HailStone parse(String line) {
    String[] parts = line.split("@");

    Point3d point = Point3d.parse(parts[0].trim());
    Vector fallingVector = Point3d.parse(parts[1].trim()).asVector();
    return new HailStone(point, fallingVector);
  }

  public Point2d intersectWithIn2d(HailStone other) {
    Vector thisLine = getLineVector();
    Vector otherLine = other.getLineVector();

    return thisLine.intersect(otherLine);
  }

  private Vector getLineVector() {
    // e = (a2-b2 b1-a1 a1b2-b1a2)
    return new Vector(
        fallingVector.getY(),
        -fallingVector.getX(),
        fallingVector.getY() * position.getX() - fallingVector.getX() * position.getY());
    //    Point3d nextPosition = move().getPosition();
    //    return new Vector(
    //        position.getY() - nextPosition.getY(),
    //        nextPosition.getX() - position.getX(),
    //        position.getX() * nextPosition.getY() - nextPosition.getX() * position.getY());
  }

  public HailStone move() {
    return new HailStone(position.move(fallingVector), fallingVector);
  }

  private Point2d to2d() {
    return new Point2d(position.getX(), position.getY());
  }

  public boolean inFuture(Point2d point) {
    return position.to2d().equals(point)
        || (Math.signum(point.getX() - position.getX()) == Math.signum(fallingVector.getX())
            && Math.signum(point.getY() - position.getY()) == Math.signum(fallingVector.getY()));
  }
}
