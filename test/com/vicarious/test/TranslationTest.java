package com.vicarious.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.vicarious.Translation;

public class TranslationTest {
	Translation t = new Translation();

	@Test
	public void testStartingWithConsonant() {

		String inputString = "banana";
		String outputString = t.getPigTailTranslation(inputString);
		assertEquals(outputString, "ananabay");

	}

	@Test
	public void testStartingWithVowels() {
		String inputString = "eat";
		String outputString = t.getPigTailTranslation(inputString);
		assertEquals(outputString, "eatyay");

	}

	@Test
	public void testWithPunctuations() {
		String inputString = "This, is a good exercise!";
		String outputString = t.getPigTailTranslation(inputString);
		assertEquals(outputString, "isThay, isyay ayay oodgay exerciseyay!");

	}

	@Test
	public void testWithOnlyPunctuation() {
		String inputString = "!";
		String outputString = t.getPigTailTranslation(inputString);
		assertEquals(outputString, "!");

	}

	@Test
	public void testWithEmptyString() {
		String inputString = "";
		String outputString = t.getPigTailTranslation(inputString);
		assertEquals(outputString, "");

	}

}
