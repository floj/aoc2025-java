package com.github.floj.aoc2025.day03;

import java.io.IOException;

import com.github.floj.aoc2025.common.PuzzleInput;

public class Day03 {

	public static void main(String[] args) throws IOException {
		var lines = PuzzleInput.readAllLines("/inputs/day03/input.txt");
		// part 1
		{
			var sum = 0;
			for (var line : lines) {
				var pack = BatteryPack.fromLine(line);
				var maxJoltage = pack.findMaxJoltage();
				System.out.format("%s [%d]\n", line, maxJoltage);
				sum += maxJoltage;
			}
			System.out.format("sum joltage: %d\n", sum);
		}
	}
}
