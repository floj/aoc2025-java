package com.github.floj.aoc2025.day07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.github.floj.aoc2025.common.PuzzleInput;

public class Day07 {

  public static void main(String[] args) throws IOException {
    var lines = PuzzleInput.readAllLines("/inputs/day07/input.txt");
    part1(lines);

    part2(lines);
  }

  private static void part2(LinkedList<String> lines) {
    var grid = new Grid(lines.toArray(i -> new String[i]));
    var start = grid.findSymbol('S');

    var timelines = countTimelines(start, grid, new HashMap<>());
    System.out.format("timelines: %d\n", timelines);
  }

  private static long countTimelines(Cell cell, Grid grid, Map<Cell, Long> cache) {
    var cached = cache.get(cell);
    if (cached != null) {
      return cached;
    }

    //    System.out.format("checking %s\n", cell);
    var nextCell = cell.getCell(0, 1);
    if (nextCell == null) {
      return 1;
    }

    if (nextCell.getSymbol() == '.') {
      var tl = countTimelines(nextCell, grid, cache);
      cache.put(cell, tl);
      return tl;
    }
    if (nextCell.getSymbol() == '^') {
      var timelines = 0L;
      for (var offsetX : new int[] {-1, 1}) {
        var c = nextCell.getCell(offsetX, 0);
        if (c != null) {
          var tl = countTimelines(c, grid, cache);
          timelines += tl;
        }
      }
      cache.put(cell, timelines);
      return timelines;
    }
    throw new RuntimeException("unknown sym: " + cell.getSymbol());
  }

  private static void part1(LinkedList<String> lines) {
    var grid = new Grid(lines.toArray(i -> new String[i]));

    var start = grid.findSymbol('S');
    var cells = List.of(start);
    var numSplits = 0;
    while (!cells.isEmpty()) {
      //      System.out.println(grid);
      //      System.out.println("#".repeat(20));
      var nextCells = new ArrayList<Cell>();

      for (var cell : cells) {
        var nextCell = cell.getCell(0, 1);
        if (nextCell == null) {
          continue;
        }
        if (nextCell.getSymbol() == '.') {
          grid.setSymbol(nextCell.getCoordinate(), '|');
          nextCells.add(nextCell);
          continue;
        }
        if (nextCell.getSymbol() == '^') {
          var wasSplit = false;
          for (var offsetX : new int[] {-1, 1}) {
            var sideCell = nextCell.getCell(offsetX, 0);
            if (sideCell == null) {
              continue;
            }
            nextCells.add(sideCell);
            var oldSym = grid.setSymbol(sideCell.getCoordinate(), '|');
            wasSplit = wasSplit || oldSym == '.';
          }
          if (wasSplit) {
            numSplits++;
          }
        }
      }
      cells = nextCells;
    }
    System.out.format("splits: %d\n", numSplits);
  }
}
