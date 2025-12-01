package com.github.floj.aoc2025.day01;

import java.io.IOException;

import com.github.floj.aoc2025.common.PuzzleInput;

public class Day01 {
	public static void main(String[] args) throws IOException {
		var lines = PuzzleInput.readAllLines("/inputs/day01/input.txt");
		var dial = new Dial(50);
		var isZero = 0;
		for (var line : lines) {
			var startPos = dial.getPosition();
			var direction = line.charAt(0);
			var clicks = Integer.parseInt(line.substring(1), 10);
			switch (direction) {
			case 'L' -> dial.dialLeft(clicks);
			case 'R' -> dial.dialRight(clicks);
			default -> throw new RuntimeException("invalid direction: " + line);
			}
			System.out.format("Current pos=%d direction=%s clicks=%d endPos=%d\n", startPos, direction, clicks,
					dial.getPosition());
			if (dial.isZero()) {
				isZero++;
			}
		}
		System.out.format("Stopped as Zero %d times\n", isZero);
		System.out.format("Passed zero %d times\n", dial.getZeroPasses());
	}
}
