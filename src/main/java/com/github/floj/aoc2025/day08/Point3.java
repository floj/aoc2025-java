package com.github.floj.aoc2025.day08;

public record Point3(int x, int y, int z) implements Comparable<Point3> {
  public static Point3 parse(String line) {
    var parts = line.split(",");
    if (parts.length != 3) {
      throw new IllegalArgumentException("invalid point definition: " + line);
    }

    var x = Integer.parseInt(parts[0]);
    var y = Integer.parseInt(parts[1]);
    var z = Integer.parseInt(parts[2]);
    return new Point3(x, y, z);
  }

  public double distanceTo(Point3 other) {
    var s = Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2);
    return Math.sqrt(s);
  }

  @Override
  public final String toString() {
    return "(%d,%d,%d)".formatted(x, y, z);
  }

  @Override
  public int compareTo(Point3 other) {
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

    {
      var v = Integer.compare(z, other.z);
      if (v != 0) {
        return v;
      }
    }

    return 0;
  }
}
