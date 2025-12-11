package com.github.floj.aoc2025.day11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Patchbay {
  final String name;
  final Set<String> outlets;
  final Map<String, Long> cache = new HashMap<>();

  public Patchbay(String name, Set<String> out) {
    this.name = name;
    outlets = out;
  }

  static Patchbay parse(String line) {
    var parts = line.split("\\s*:\\s*");
    if (parts.length != 2) {
      throw new IllegalArgumentException("invalid line: " + line);
    }
    var name = parts[0];
    var outs = parts[1].split("\\s+");
    var out = Arrays.stream(outs).map(String::trim).collect(Collectors.toSet());
    return new Patchbay(name, out);
  }

  String name() {
    return name;
  }

  int sumPathTo(String dest, Map<String, Patchbay> bays) {
    if (outlets.contains(dest)) {
      return 1;
    }
    var sum = 0;
    for (var o : outlets) {
      sum += bays.get(o).sumPathTo(dest, bays);
    }
    return sum;
  }

  long sumPathToOutVia(String dest, Map<String, Patchbay> bays, SortedSet<String> wants) {
    if (wants.contains(name)) {
      wants = new TreeSet<>(wants);
      wants.remove(name);
    }
    var paths = cache.get(wants.toString());
    if (paths != null) {
      return paths;
    }

    if (outlets.contains(dest)) {
      if (wants.isEmpty()) {
        return 1;
      }
      return 0;
    }
    var sum = 0L;
    for (var o : outlets) {
      sum += bays.get(o).sumPathToOutVia(dest, bays, wants);
    }
    cache.put(wants.toString(), sum);
    return sum;
  }
}
