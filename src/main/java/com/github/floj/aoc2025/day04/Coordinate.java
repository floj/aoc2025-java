package com.github.floj.aoc2025.day04;

public record Coordinate(int x, int y) {

  Coordinate add(int offsetX, int offsetY) {
    return new Coordinate(x + offsetX, y + offsetY);
  }

  Coordinate add(Coordinate coordinate) {
    return add(coordinate.x, coordinate.y);
  }
}
