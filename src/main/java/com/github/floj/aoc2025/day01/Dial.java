package com.github.floj.aoc2025.day01;

public class Dial {

	private final int TURNS = 100;
	private int position;
	private int zeroPasses;

	public Dial(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	public int getZeroPasses() {
		return zeroPasses;
	}

	public boolean isZero() {
		return position == 0;
	}

	/**
	 * Turn the dial left.
	 * 
	 * @param clicks number of clicks to turn
	 * @return the new position after the turning
	 */
	public int dialLeft(int clicks) {
		while (clicks > 0) {
			clicks--;
			position = position - 1;
			if (position == 0) {
				zeroPasses++;
			}
			if (position < 0) {
				position = TURNS - 1;
			}
		}
		return position;
	}

	/**
	 * Turn the dial tight.
	 * 
	 * @param clicks number of clicks to turn
	 * @return the new position after the turning
	 */
	public int dialRight(int clicks) {
		while (clicks > 0) {
			clicks--;
			position = position + 1;
			if (position == TURNS) {
				position = 0;
				zeroPasses++;
			}
		}
		return position;
	}
}
