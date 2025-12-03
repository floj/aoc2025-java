package com.github.floj.aoc2025.day03;

public record BatteryPack(int[] cells) {

	public static BatteryPack fromLine(String line) {
		var cells = line.chars().map(i -> i - '0').toArray();
		return new BatteryPack(cells);
	}

	public int findMaxJoltage() {
		var max = cells[0] * 10 + cells[1];
		for (var i = 2; i < cells.length; i++) {
			var cell = cells[i];
			var h = max / 10;
			var l = max % 10;

			var p1 = h * 10 + cell;
			var p2 = l * 10 + cell;

			var pMax = Math.max(p1, p2);
			max = Math.max(max, pMax);
		}
		return max;
	}
}
