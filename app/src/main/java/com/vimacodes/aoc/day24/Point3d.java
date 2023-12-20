package com.vimacodes.aoc.day24;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.List;
import lombok.Value;

@Value
class Point3d {
  double x;
  double y;
  double z;

  public Point3d(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Point3d(List<Long> coordinates) {
    Preconditions.checkNotNull(coordinates);
    Preconditions.checkArgument(coordinates.size() == 3);

    x = coordinates.get(0);
    y = coordinates.get(1);
    z = coordinates.get(2);
  }

  public static Point3d parse(String str) {
    List<Long> coordinates =
        Arrays.stream(str.split(",")).map(String::trim).map(Long::parseLong).toList();
    return new Point3d(coordinates);
  }

  public Point3d move(Vector vector) {
    return new Point3d(x + vector.getX(), y + vector.getY(), z + vector.getZ());
  }

  public Vector asVector() {
    return new Vector(x, y, z);
  }

  public Point2d to2d() {
    return new Point2d(x, y);
  }
}
