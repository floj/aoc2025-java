package com.github.floj.aoc2025.day02;

import java.util.Iterator;

public record Range(long start, long end) implements Iterable<Long> {

	public static Range parse(String s) {
		var parts = s.split("-", 2);
		if (parts.length != 2) {
			throw new IllegalArgumentException("invalid range: " + s);
		}
		var start = Long.parseLong(parts[0], 10);
		var end = Long.parseLong(parts[1], 10);
		if (start > end) {
			throw new IllegalArgumentException("start must be lower or equal to end: " + s);
		}
		return new Range(start, end);
	}

	@Override
	public Iterator<Long> iterator() {
		return new Iterator<>() {
			long current = start;

			@Override
			public Long next() {
				var v = current;
				current++;
				return v;
			}

			@Override
			public boolean hasNext() {
				return current <= end;
			}
		};
	}

}
