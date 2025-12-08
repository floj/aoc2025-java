package com.github.floj.aoc2025.day08;

public record StraightLine(Point3 p1, Point3 p2) implements Comparable<StraightLine> {

  public static StraightLine create(Point3 p1, Point3 p2) {
    if (p1.equals(p2)) {
      return null;
    }
    if (p1.compareTo(p2) < 0) {
      return new StraightLine(p1, p2);
    }
    return new StraightLine(p2, p1);
  }

  public double distance() {
    return p1.distanceTo(p2);
  }

  @Override
  public int compareTo(StraightLine other) {
    {
      var v = p1.compareTo(other.p1);
      if (v != 0) {
        return v;
      }
    }

    {
      var v = p2.compareTo(other.p2);
      if (v != 0) {
        return v;
      }
    }
    return 0;
  }
}
