package com.github.floj.aoc2025.day06;

import static org.assertj.core.api.Assertions.entry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.floj.aoc2025.common.PuzzleInput;

public class Day06 {

  static final Map<Character, BinaryOperator<Long>> OPERATORS =
      Map.ofEntries(entry('+', (a, b) -> a + b), entry('*', (a, b) -> a * b));

  public static void main(String[] args) throws IOException {
    var lines = PuzzleInput.readAllLines("/inputs/day06/input.txt");
    part1(lines);
    part2(lines);
  }

  private static char[][] rotateCounterClockwise(char[][] a) {
    var rows = a.length;
    var cols = a[0].length;

    var rotated = new char[cols][rows];
    for (var r = 0; r < rows; r++) {
      for (var c = 0; c < cols; c++) {
        rotated[cols - 1 - c][r] = a[r][c];
      }
    }
    return rotated;
  }

  private static void part2(LinkedList<String> lines) {
    var org = lines.stream().map(String::toCharArray).toArray(i -> new char[i][]);
    var rotated = rotateCounterClockwise(org);

    var total = 0L;
    var nums = new ArrayList<Long>();
    for (var chars : rotated) {
      var line = String.valueOf(chars).trim();
      if (line.isBlank()) {
        continue;
      }
      System.out.format("checking line %s\n", new String(chars));
      BinaryOperator<Long> op = null;
      var v = 0L;
      for (char c : line.toCharArray()) {
        if (c >= '0' && c <= '9') {
          v = v * 10 + c - '0';
        }
        op = OPERATORS.get(c);
      }
      System.out.format("adding %s\n", v);
      nums.add(v);
      if (op == null) {
        continue;
      }
      System.out.format("applying operation to %s\n", nums);
      var sum = nums.stream().collect(Collectors.reducing(op)).get();
      System.out.format("sum: %d\n\n", sum);
      nums.clear();
      total += sum;
    }
    System.out.format("total: %d\n", total);
  }

  private static void part1(LinkedList<String> lines) {
    var fields =
        lines.stream()
            .map(line -> line.split("\\s+"))
            .map(f -> Stream.of(f).map(String::trim).filter(s -> !s.isBlank()).toList())
            .collect(Collectors.toCollection(LinkedList::new));
    var operators = fields.pollLast().stream().map(o -> o.charAt(0)).map(OPERATORS::get).toList();
    var operands = fields.stream().map(l -> l.stream().map(Long::valueOf).toList()).toList();
    var total = 0L;
    for (var i = 0; i < operators.size(); i++) {
      var reducer = operators.get(i);
      var operandsItr = operands.iterator();
      var sum = operandsItr.next().get(i);

      while (operandsItr.hasNext()) {
        var v = operandsItr.next().get(i);
        sum = reducer.apply(sum, v);
      }
      total += sum;
    }
    System.out.format("total: %d\n", total);
  }
}
