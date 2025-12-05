package com.github.floj.aoc2025.day05;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import com.github.floj.aoc2025.common.PuzzleInput;

public class Day05 {

  public static void main(String[] args) throws IOException {
    var lines = PuzzleInput.readAllLines("/inputs/day05/input.txt");
    var lineItr = lines.iterator();

    // read ranges
    var ranges = new ArrayList<Range>();
    while (lineItr.hasNext()) {
      var line = lineItr.next();
      // end of range definitions found
      if (line.isBlank()) {
        break;
      }
      var range = Range.parse(line);
      ranges.add(range);
    }

    // check if IDs are fresh
    var freshIds = new TreeSet<Long>();
    while (lineItr.hasNext()) {
      var line = lineItr.next();
      var id = Long.parseLong(line, 10);

      var isFresh = ranges.stream().anyMatch(range -> range.includes(id));
      if (isFresh) {
        freshIds.add(id);
      }
    }

    System.out.format("fresh: %d\n", freshIds.size());

    var numFreshIds = 0L;
    var rangeItr = ranges.stream().sorted().iterator();
    var currentRange = rangeItr.next();
    while (rangeItr.hasNext()) {
      var range = rangeItr.next();
      if (range.overlaps(currentRange)) {
        var combined = currentRange.combine(range);
        // System.out.printf("combining %s with %s to %s\n", currentRange, range, combined);
        currentRange = combined;
        continue;
      }
      numFreshIds += currentRange.size();
      currentRange = range;
    }
    numFreshIds += currentRange.size();

    System.out.format("num fresh IDs: %d\n", numFreshIds);
  }
}
