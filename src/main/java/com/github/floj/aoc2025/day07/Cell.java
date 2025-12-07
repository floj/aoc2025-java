package com.github.floj.aoc2025.day07;

import java.util.Objects;

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

  public Cell getCell(int offsetX, int offsetY) {
    return grid.getCell(coordinate.add(offsetX, offsetY));
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public int x() {
    return coordinate.x();
  }

  public int y() {
    return coordinate.y();
  }

  @Override
  public int hashCode() {
    return coordinate.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    var other = (Cell) obj;
    return Objects.equals(coordinate, other.coordinate);
  }

  @Override
  public String toString() {
    return "(%d,%d) %s".formatted(coordinate.x(), coordinate.y(), symbol);
  }
}
