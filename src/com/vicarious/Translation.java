package com.vicarious;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/PigLatin")
public class Translation {

	enum EndString {
		VOWEL_END_STRING("yay"), CONSONANT_END_STRING("ay");
		private String str;

		EndString(String s) {
			str = s;
		}

		public String getValue() {
			return str;
		}
	}

	public static ArrayList<Character> vowelArray = new ArrayList<Character>(
			Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

	@POST
	@Path("/getTranslation")
	@Produces("text/plain")
	public String getPigTailTranslation(String inputString) {

		String[] words = inputString.split("\\s+");
		StringBuffer outputString = new StringBuffer();
		for (int i = 0; i < words.length; i++) {
			Pattern pattern = Pattern.compile("(\\w*\\p{Punct})\\w*");
			Matcher matcher = pattern.matcher(words[i]);
			if (matcher.find()) {
				String[] sub_words = words[i].split("\\b");
				StringBuffer subOutputString = new StringBuffer();
				for (int j = 0; j < sub_words.length; j++) {
					StringBuffer subOutputWord = translate(new StringBuffer(
							sub_words[j]));
					subOutputString.append(subOutputWord);
				}
				outputString.append(subOutputString + " ");
			} else {
				StringBuffer outputWord = translate(new StringBuffer(words[i]));
				outputString.append(outputWord + " ");
			}
		}
		return outputString.toString().trim();
	}

	private StringBuffer translate(StringBuffer inputString) {
		int firstOccurenceOfVowel = -1;
		int start = 0;
		if (inputString.length() == 0) {
			return inputString;
		}
		if (Pattern.matches("\\p{Punct}+", inputString)) {
			return inputString;
		}

		// Perform PigTail Translation for the inputString
		for (int i = 0; i < inputString.length(); i++) {
			boolean isVowel = isVowel(inputString.charAt(i));
			if (isVowel) {
				firstOccurenceOfVowel = i;
				break;
			}
		}

		if (firstOccurenceOfVowel != -1) {
			CharSequence suffix = inputString.subSequence(start,
					firstOccurenceOfVowel);
			inputString = inputString.delete(start, firstOccurenceOfVowel);
			inputString.append(suffix);
		}
		String endString = firstOccurenceOfVowel == 0 ? EndString.VOWEL_END_STRING
				.getValue() : EndString.CONSONANT_END_STRING.getValue();
		return inputString.append(endString);
	}

	private boolean isVowel(char character) {
		if (vowelArray.contains(character))
			return true;
		else
			return false;
	}
}