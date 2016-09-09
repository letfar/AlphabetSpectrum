package com.letfar.alphabetspectrum.algorithm;

import java.util.*;

/**
 * Created by Alex on 03.09.2016.
 */
public class AlphabetCharCounter {
    public static final String EN_LOW_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static Map<Character, Integer> countAlphabetChars(String text) {
        return countAlphabetChars(text, EN_LOW_ALPHABET.toCharArray());
    }

    public static Map<Character, Integer> countAlphabetChars(String text, char[] alphabet) {
        Map<Character, Integer> resultMap = new HashMap<>();
        Map<Character, Integer> countedCharsInText = CharCounter.countChars(text);

        Integer characterCount;
        boolean textHasThisChar;

        for (char character : alphabet) {

            textHasThisChar = countedCharsInText.containsKey(character);

            characterCount = (textHasThisChar)
                    ? countedCharsInText.get(character)
                    : 0;

            resultMap.put(character, characterCount);
        }

        return resultMap;
    }


    public static String toString(Map<Character, ? extends Number> countedCharsMap) {
        StringBuilder stringBuilder = new StringBuilder();

        TreeMap<Character, Number> sortedByAlphabetMap = new TreeMap<>(countedCharsMap);

        for (Map.Entry<Character, Number> entry : sortedByAlphabetMap.entrySet()) {

            Character character = entry.getKey();
            Number characterInfo = entry.getValue();

            stringBuilder
                    .append(character)
                    .append("[").append(characterInfo).append("] ");
        }

        // Remove last space
        stringBuilder.setLength(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    public static Map<Character, Double> asPercents(Map<Character, Integer> countedCharsMap) {
        Map<Character, Double> resultMap = new TreeMap<>();

        Collection<Integer> counterValues = countedCharsMap.values();

        // Get counters sum
        int countersSum = 0;
        for (Integer counterValue : counterValues)
            countersSum += counterValue;

        for (Map.Entry<Character, Integer> entry : countedCharsMap.entrySet())
            resultMap.put(
                    entry.getKey(),
                    entry.getValue().doubleValue() / countersSum);

        return resultMap;
    }
}
