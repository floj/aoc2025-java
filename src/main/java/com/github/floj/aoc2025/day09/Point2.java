package com.github.floj.aoc2025.day09;

public record Point2(int x, int y) implements Comparable<Point2> {
  public static Point2 parse(String line) {
    var parts = line.split(",");
    if (parts.length != 2) {
      throw new IllegalArgumentException("invalid point definition: " + line);
    }

    var x = Integer.parseInt(parts[0]);
    var y = Integer.parseInt(parts[1]);
    return new Point2(x, y);
  }

  @Override
  public final String toString() {
    return "(%d,%d)".formatted(x, y);
  }

  @Override
  public int compareTo(Point2 other) {
    {
      var v = Integer.compare(x, other.x);
      if (v != 0) {
        return v;
      }
    }
    {
      var v = Integer.compare(y, other.y);
      if (v != 0) {
        return v;
      }
    }
    return 0;
  }
}
