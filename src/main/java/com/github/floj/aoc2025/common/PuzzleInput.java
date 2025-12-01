package com.github.floj.aoc2025.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PuzzleInput {

	public static List<String> readAllLines(String resource) throws IOException {
		try (var rin = PuzzleInput.class.getResourceAsStream(resource);
				var isr = new InputStreamReader(rin, StandardCharsets.UTF_8);
				var br = new BufferedReader(isr)) {
			return br.readAllLines();
		}
	}
}
