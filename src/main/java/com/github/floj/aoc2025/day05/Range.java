package com.github.floj.aoc2025.day05;

public record Range(long start, long end) implements Comparable<Range> {

  public static Range parse(String s) {
    var parts = s.split("-", 2);
    if (parts.length != 2) {
      throw new IllegalArgumentException("invalid range: " + s);
    }
    var start = Long.parseLong(parts[0], 10);
    var end = Long.parseLong(parts[1], 10);
    if (start > end) {
      throw new IllegalArgumentException("start must be lower or equal to end: " + s);
    }
    return new Range(start, end);
  }

  public boolean includes(long v) {
    return v >= start && v <= end;
  }

  public long size() {
    return end - start + 1;
  }

  public boolean overlaps(Range other) {
    return Math.max(start, other.start) <= Math.min(end, other.end);
  }

  public Range combine(Range other) {
    return new Range(Math.min(start, other.start), Math.max(end, other.end));
  }

  @Override
  public int compareTo(Range other) {
    var c = Long.compare(start, other.start);
    if (c != 0) {
      return c;
    }

    return Long.compare(end, other.end);
  }
}
