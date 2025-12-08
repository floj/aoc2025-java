package com.github.floj.aoc2025.day08;

import static java.util.stream.Collectors.toCollection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import com.github.floj.aoc2025.common.PuzzleInput;

public class Day08 {

  public static void main(String[] args) throws IOException {
    //    var puzzle = "sample.txt";
    //    var connect = 10;

    var puzzle = "input.txt";
    var connect = 1000;

    var lines = PuzzleInput.readAllLines("/inputs/day08/" + puzzle);

    //    var distances = new HashMap<StraightLine, Double>();
    var distances = new TreeMap<Double, StraightLine>();

    var points = lines.stream().map(Point3::parse).toList();
    for (var p1 : points) {
      for (var p2 : points) {
        var line = StraightLine.create(p1, p2);
        if (line == null) {
          continue;
        }

        var existing = distances.put(line.distance(), line);
        if (existing != null && !existing.equals(line)) {
          throw new IllegalStateException(
              "same distance " + line.distance() + " found: " + existing + " <=>" + line);
        }
      }
    }

    System.out.format("num distances: %d\n", distances.size());
    // check that we do not have duplicate distances

    part1(connect, new TreeMap<>(distances));
    part2(new TreeMap<>(distances), points.size());
  }

  private static void part1(int connect, SortedMap<Double, StraightLine> distances) {
    var circuits = new HashMap<Point3, Integer>();
    var nextCircuit = 1;
    //    while (!distances.isEmpty()) {
    for (var i = 0; i < connect && !distances.isEmpty(); i++) {
      var v = distances.pollFirstEntry();
      var line = v.getValue();
      //      System.out.println("checking " + line + " with distance " + v.getKey());

      var c1 = circuits.get(line.p1());
      var c2 = circuits.get(line.p2());
      // new circuit
      if (c1 == null && c2 == null) {
        circuits.put(line.p1(), nextCircuit);
        circuits.put(line.p2(), nextCircuit);
        nextCircuit++;
        continue;
      }
      // both are already connected
      if (c1 == c2) {
        continue;
      }
      if (c1 != null && c2 != null) {
        // combine circuits
        var updatePoints =
            circuits.entrySet().stream()
                .filter(e -> e.getValue().equals(c2))
                .map(Entry::getKey)
                .toList();
        updatePoints.forEach(p -> circuits.put(p, c1));
      }
      // either c1 or c2 is set
      var c = c1 != null ? c1 : c2;
      circuits.put(line.p1(), c);
      circuits.put(line.p2(), c);
    }

    var circuitByNum = groupCircuitsByNum(circuits);

    //    circuitByNum.forEach((n, p) -> System.out.println(n + " " + p));

    var circuitSizes =
        circuitByNum.values().stream().map(Set::size).collect(toCollection(TreeSet::new));
    var itr = circuitSizes.descendingIterator();
    var sum = itr.next();
    for (var i = 1; i < 3; i++) {
      sum *= itr.next();
    }

    System.out.println("sum " + sum);
  }

  private static void part2(SortedMap<Double, StraightLine> distances, int numPoints) {
    var circuits = new HashMap<Point3, Integer>();

    var nextCircuit = 1;
    while (!distances.isEmpty()) {
      var v = distances.pollFirstEntry();
      var line = v.getValue();
      //      System.out.println("checking " + line + " with distance " + v.getKey());

      var c1 = circuits.get(line.p1());
      var c2 = circuits.get(line.p2());
      // new circuit
      if (c1 == null && c2 == null) {
        circuits.put(line.p1(), nextCircuit);
        circuits.put(line.p2(), nextCircuit);
        nextCircuit++;
      }
      // both are already connected
      if (c1 == c2) {
        continue;
      }
      if (c1 != null && c2 != null) {
        // combine circuits
        var updatePoints =
            circuits.entrySet().stream()
                .filter(e -> e.getValue().equals(c2))
                .map(Entry::getKey)
                .toList();
        updatePoints.forEach(p -> circuits.put(p, c1));

        if (allConnected(numPoints, circuits)) {
          System.out.println("all connected");
          System.out.println((long) line.p1().x() * (long) line.p2().x());
          return;
        }
      }
      // either c1 or c2 is set
      var c = c1 != null ? c1 : c2;
      circuits.put(line.p1(), c);
      circuits.put(line.p2(), c);
      if (allConnected(numPoints, circuits)) {
        System.out.println("all connected");
        System.out.println((long) line.p1().x() * (long) line.p2().x());
        return;
      }
    }
  }

  private static boolean allConnected(int numPoints, HashMap<Point3, Integer> circuits) {
    var byNum = groupCircuitsByNum(circuits);
    if (byNum.size() != 1) {
      return false;
    }
    var points = byNum.firstEntry().getValue();
    return points.size() == numPoints;
  }

  private static SortedMap<Integer, Set<Point3>> groupCircuitsByNum(Map<Point3, Integer> circuits) {
    var circuitByNum = new TreeMap<Integer, Set<Point3>>();
    circuits.forEach(
        (point, num) -> circuitByNum.computeIfAbsent(num, _ -> new TreeSet<>()).add(point));
    return circuitByNum;
  }
}
