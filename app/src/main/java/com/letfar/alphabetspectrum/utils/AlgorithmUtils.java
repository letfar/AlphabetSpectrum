package com.letfar.alphabetspectrum.utils;

import com.letfar.alphabetspectrum.algorithm.AlphabetCharCounter;

import java.util.Map;

/**
 * Created by Alex on 07.09.2016.
 */
public class AlgorithmUtils {
    public static Map<Character, Double> findSpectrum(String text) {
        final Map<Character, Integer> countedCharsMap = AlphabetCharCounter.countAlphabetChars(text);
        return AlphabetCharCounter.asPercents(countedCharsMap);
    }
}
