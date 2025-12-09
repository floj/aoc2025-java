package com.github.floj.aoc2025.day09;

import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.github.floj.aoc2025.common.PuzzleInput;

public class Day09 {

  public static void main(String[] args) throws IOException {
    //    var puzzle = "sample.txt";
    var puzzle = "input.txt";

    var lines = PuzzleInput.readAllLines("/inputs/day09/" + puzzle);

    var areas = new TreeMap<Long, Set<Rect>>();

    var points = lines.stream().map(Point2::parse).toList();
    for (var p1 : points) {
      for (var p2 : points) {
        var rect = Rect.create(p1, p2);
        if (rect == null) {
          continue;
        }
        areas.computeIfAbsent(rect.area(), _ -> new TreeSet<>()).add(rect);
      }
    }

    System.out.format("num areas: %d\n", areas.size());
    // get the one with the highest distance
    var last = areas.lastEntry();

    System.out.println("largest rect " + last.getKey() + " =>" + last.getValue());
  }
}
