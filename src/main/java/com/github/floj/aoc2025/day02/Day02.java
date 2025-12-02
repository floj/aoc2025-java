package com.github.floj.aoc2025.day02;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

import com.github.floj.aoc2025.common.PuzzleInput;

public class Day02 {

	public static void main(String[] args) throws IOException {
		var input = PuzzleInput.readString("/inputs/day02/input.txt");
		var rangeStrings = input.split(",");
		var ranges = Arrays.stream(rangeStrings).map(Range::parse).toList();
		var sumPart1 = 0L;
		var sumPart2 = 0L;
		for (var range : ranges) {
			for (var num : range) {
				if (isInvalidPart1(num)) {
					sumPart1 = sumPart1 + num;
				}
				if (isInvalidPart2(num)) {
					sumPart2 = sumPart2 + num;
				}
			}
		}
		System.out.printf("Sum part 1: %d\n", sumPart1);
		System.out.printf("Sum part 2: %d\n", sumPart2);
	}

	private static boolean isInvalidPart1(long num) {
		var s = Long.toString(num);
		if (s.length() % 2 != 0) {
			return false;
		}
		var mid = s.length() / 2;
		var firstHalf = s.substring(0, mid);
		var secondHalf = s.substring(mid);
		return firstHalf.equals(secondHalf);
	}

	private static final Pattern p = Pattern.compile("^(\\d+)(\\1)+$");

	private static boolean isInvalidPart2(long num) {
		var s = Long.toString(num);
		return p.matcher(s).matches();
	}

}
