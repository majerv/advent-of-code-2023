package com.vimacodes.aoc.day24;

import lombok.Value;

/** Similar to {@link com.vimacodes.aoc.day24.Point3d} but with different operations. */
@Value
class Vector {

  double x;
  double y;
  double z;

  public Point2d intersect(Vector otherVector) {
    // v1 = (a b c)
    // v2 = (e f g)
    double a = x;
    double b = y;
    double c = z;

    double e = otherVector.x;
    double f = otherVector.y;
    double g = otherVector.z;

    // P = (bg - fc ec-ag af-eb)
    // -->
    // x = (fc-bg)/(af-eb)
    // y = (ag-ec)/(af-eb)
    double af_eb = a * f - e * b;

    double x, y;
    if (af_eb == 0) {
      x = 0;
      y = 0;
    } else {
      x = (f * c - b * g) / af_eb;
      y = (a * g - e * c) / af_eb;
    }

    return new Point2d(x, y);
  }
}
