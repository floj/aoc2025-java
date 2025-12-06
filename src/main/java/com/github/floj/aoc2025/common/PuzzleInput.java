package com.github.floj.aoc2025.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class PuzzleInput {

  public static LinkedList<String> readAllLines(String resource) throws IOException {
    try (var rin = PuzzleInput.class.getResourceAsStream(resource);
        var isr = new InputStreamReader(rin, StandardCharsets.UTF_8);
        var br = new BufferedReader(isr)) {
      var lines = br.readAllLines();
      return new LinkedList<>(lines);
    }
  }

  public static String readString(String resource) throws IOException {
    try (var rin = PuzzleInput.class.getResourceAsStream(resource);
        var isr = new InputStreamReader(rin, StandardCharsets.UTF_8);
        var br = new BufferedReader(isr)) {
      return br.readAllAsString();
    }
  }
}
