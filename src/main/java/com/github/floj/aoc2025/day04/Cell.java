package com.github.floj.aoc2025.day04;

import java.util.ArrayList;
import java.util.List;

public class Cell {
  private final Coordinate coordinate;
  private final char symbol;

  private final Grid grid;

  public Cell(Coordinate coordinate, char symbol, Grid grid) {
    this.coordinate = coordinate;
    this.symbol = symbol;
    this.grid = grid;
  }

  public char getSymbol() {
    return symbol;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  private static final List<Coordinate> neighborOffsets =
      List.of(
          new Coordinate(-1, -1),
          new Coordinate(0, -1),
          new Coordinate(1, -1),
          new Coordinate(-1, 0),
          new Coordinate(1, 0),
          new Coordinate(-1, 1),
          new Coordinate(0, 1),
          new Coordinate(1, 1));

  public List<Cell> getNeighbors() {
    var neighbors = new ArrayList<Cell>();
    for (var offset : neighborOffsets) {
      var neighborCoord = coordinate.add(offset);
      var neighbor = grid.getCell(neighborCoord);
      if (neighbor != null) {
        neighbors.add(neighbor);
      }
    }
    return neighbors;
  }

  @Override
  public String toString() {
    return "(%d,%d) %s".formatted(coordinate.x(), coordinate.y(), symbol);
  }
}
