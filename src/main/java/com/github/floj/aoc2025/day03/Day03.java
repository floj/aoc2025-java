package com.github.floj.aoc2025.day03;

import java.io.IOException;

import com.github.floj.aoc2025.common.PuzzleInput;

public class Day03 {

  public static void main(String[] args) throws IOException {
    var lines = PuzzleInput.readAllLines("/inputs/day03/input.txt");
    var sumPart1 = 0L;
    var sumPart2 = 0L;
    for (var line : lines) {
      var pack = BatteryPack.fromLine(line);
      var joltage2 = pack.findMaxJoltage();
      var joltage12 = pack.findMaxJoltage(12);
      System.out.format("%s [%d] [%d]\n", line, joltage2, joltage12);

      sumPart1 += joltage2;
      sumPart2 += joltage12;
    }
    System.out.format("sum joltage: %d\n", sumPart1);
    System.out.format("sum multi joltage: %d\n", sumPart2);
  }
}
