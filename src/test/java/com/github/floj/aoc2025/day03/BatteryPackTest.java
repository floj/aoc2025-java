package com.github.floj.aoc2025.day03;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BatteryPackTest {

	@ParameterizedTest
	@CsvSource({ "987654321111111, 98", "811111111111119, 89", "234234234234278, 78", "818181911112111, 92" })
	void test(String line, int expectedJoltage) {
		var joltage = BatteryPack.fromLine(line).findMaxJoltage();
		assertThat(joltage).isEqualTo(expectedJoltage);
	}

}
