package com.github.floj.aoc2025.day03;

public record BatteryPack(int[] cells, String line) {

  public static BatteryPack fromLine(String line) {
    var cells = line.chars().map(i -> i - '0').toArray();
    return new BatteryPack(cells, line);
  }

  public long findMaxJoltage() {
    var max = cells[0] * 10 + cells[1];
    for (var i = 2; i < cells.length; i++) {
      var cell = cells[i];
      var high = max / 10;
      var low = max % 10;

      var p1 = high * 10 + cell;
      var p2 = low * 10 + cell;

      var pMax = Math.max(p1, p2);
      max = Math.max(max, pMax);
    }
    return max;
  }

  private int indexOf(int start, int search) {
    for (var i = start; i < cells.length; i++) {
      if (cells[i] == search) {
        return i;
      }
    }
    return -1;
  }

  public long findMaxJoltage(int activateCells) {
    var max = 0L;
    var pos = 0;

    var remaining = activateCells;
    while (remaining > 0) {
      var searchCharge = 9;

      while (searchCharge > 0 && remaining > 0) {
        var i = indexOf(pos, searchCharge);
        if (i >= 0 && i <= cells.length - remaining) {
          pos = i + 1;
          max = max * 10 + searchCharge;
          remaining--;
          break;
        }
        searchCharge--;
      }
    }

    return max;
  }
}
