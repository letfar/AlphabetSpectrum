package com.letfar.alphabetspectrum.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 03.09.2016.
 */
public class CharCounter {

    public static Map<Character, Integer> countChars(String text) {
        HashMap<Character, Integer> resultMap = new HashMap<>();

        if (text == null || text.isEmpty())
            return resultMap;

        for (char character : text.toCharArray()) {
            Character currentCharacter = Character.toLowerCase(character);
            Integer currentCharacterCount = resultMap.get(currentCharacter);

            if (currentCharacterCount == null)
                currentCharacterCount = 0;

            resultMap.put(
                    currentCharacter,
                    currentCharacterCount + 1);
        }

        return resultMap;
    }
}
