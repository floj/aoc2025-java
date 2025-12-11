package com.github.floj.aoc2025.day11;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.github.floj.aoc2025.common.PuzzleInput;

public class Day11 {

  public static void main(String[] args) throws IOException {
    //    var puzzle = "sample-p1.txt";
    //    var puzzle = "sample-p2.txt";
    var puzzle = "input.txt";

    var lines = PuzzleInput.readAllLines("/inputs/day11/" + puzzle);
    var bays =
        lines.stream().map(Patchbay::parse).collect(Collectors.toMap(Patchbay::name, b -> b));

    var paths = bays.get("you").sumPathTo("out", bays);
    System.out.println("path from you to out: " + paths);

    var via = new TreeSet<>(Set.of("dac", "fft"));
    var pathsVia = bays.get("svr").sumPathToOutVia("out", bays, via);
    System.out.println("path from svr to out via " + via + ": " + pathsVia);
  }
}
