package com.github.floj.aoc2025.day09;

public record Rect(int minX, int minY, int maxX, int maxY) implements Comparable<Rect> {

  public static Rect create(Point2 p1, Point2 p2) {
    if (p1.equals(p2)) {
      return null;
    }
    var minX = Math.min(p1.x(), p2.x());
    var minY = Math.min(p1.y(), p2.y());
    var maxX = Math.max(p1.x(), p2.x());
    var maxY = Math.max(p1.y(), p2.y());

    return new Rect(minX, minY, maxX, maxY);
  }

  public long area() {
    long w = width();
    long h = height();
    return w * h;
  }

  public int width() {
    return maxX - minX + 1;
  }

  public int height() {
    return maxY - minY + 1;
  }

  @Override
  public int compareTo(Rect o) {
    var self = new long[] {minX, maxX, minY, maxY};
    var other = new long[] {o.minX, o.maxX, o.minY, o.maxY};
    for (var i = 0; i < self.length; i++) {
      var v = Long.compare(self[i], other[i]);
      if (v != 0) {
        return v;
      }
    }
    return 0;
  }
}
