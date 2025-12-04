package com.github.floj.aoc2025.day04;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Grid implements Iterable<Cell> {
  private final String[] rows;
  private final int numColumns;

  public Grid(String[] rows) {
    this.rows = rows;
    numColumns = rows[0].length();
  }

  public Grid copy() {
    return new Grid(Arrays.copyOf(rows, rows.length));
  }

  public void setSymbol(Coordinate c, char symbol) {
    // just to check bounds
    if (getCell(c) == null) {
      throw new IllegalArgumentException("invalid coordinate: " + c);
    }
    var row = rows[c.y()];
    var symbols = row.toCharArray();
    symbols[c.x()] = symbol;
    rows[c.y()] = String.copyValueOf(symbols);
  }

  public Cell getCell(Coordinate coordinate) {
    var x = coordinate.x();
    var y = coordinate.y();

    if (x < 0 || x >= numColumns) {
      return null;
    }
    if (y < 0 || y >= rows.length) {
      return null;
    }
    var symbol = rows[y].charAt(x);
    return new Cell(coordinate, symbol, this);
  }

  @Override
  public Iterator<Cell> iterator() {
    return new Iterator<>() {
      final int maxIndex = rows.length * numColumns;
      int index = 0;

      @Override
      public boolean hasNext() {
        return index < maxIndex;
      }

      @Override
      public Cell next() {
        var row = index / numColumns;
        var col = index % numColumns;
        index++;
        return getCell(new Coordinate(row, col));
      }
    };
  }

  @Override
  public String toString() {
    return Arrays.stream(rows).collect(Collectors.joining("\n"));
  }
}
