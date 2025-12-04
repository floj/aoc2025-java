package com.github.floj.aoc2025.day04;

import java.io.IOException;

import com.github.floj.aoc2025.common.PuzzleInput;

public class Day04 {

  public static void main(String[] args) throws IOException {
    var lines = PuzzleInput.readAllLines("/inputs/day04/input.txt");
    var grid = new Grid(lines.toArray(String[]::new));

    var round = 0;
    var rollsRemoved = 0;

    var done = false;
    while (!done) {
      done = true;

      var nextGrid = grid.copy();
      var accessible = 0;

      for (var cell : grid) {
        if (cell.getSymbol() != '@') {
          continue;
        }

        var paperRolls = cell.getNeighbors().stream().filter(n -> n.getSymbol() == '@').count();
        if (paperRolls < 4) {
          done = false;
          accessible++;
          nextGrid.setSymbol(cell.getCoordinate(), '.');
        }
      }
      rollsRemoved += accessible;
      System.out.println(nextGrid);
      System.out.format(
          "[round %d] accessible by forklift: %d | total: %d\n", round, accessible, rollsRemoved);
      grid = nextGrid;
      round++;
    }
  }
}
