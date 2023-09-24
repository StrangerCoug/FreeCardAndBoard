package com.github.strangercoug.freecardandboard.enums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CardSuitTest {
	@ParameterizedTest
	@MethodSource("cardSuitIsRed")
	void testIsRed(CardSuit cardSuit, boolean isRed) {
		assertThat(cardSuit.isRed(), equalTo(isRed));
	}

	private static Stream<Arguments> cardSuitIsRed() {
		return Stream.of(
				arguments(CardSuit.CLUBS, false),
				arguments(CardSuit.DIAMONDS, true),
				arguments(CardSuit.HEARTS, true),
				arguments(CardSuit.SPADES, false),
				arguments(CardSuit.BLACK, false),
				arguments(CardSuit.RED, true)
		);
	}
}
