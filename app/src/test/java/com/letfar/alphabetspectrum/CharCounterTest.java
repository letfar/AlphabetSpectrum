package com.letfar.alphabetspectrum;

import com.letfar.alphabetspectrum.algorithm.CharCounter;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Alex on 03.09.2016.
 */
public class CharCounterTest {

    @Test
    public void countChars_exampleString() throws Exception {
        Map<Character, Integer> countersMap = CharCounter.countChars("HelloWorld");
        // Get counts:
        int h = countersMap.get('h'); // 1
        int e = countersMap.get('e'); // 2
        int l = countersMap.get('l'); // 3
        int o = countersMap.get('o'); // 4
        int w = countersMap.get('w'); // 5
        int r = countersMap.get('r'); // 6
        int d = countersMap.get('d'); // 7


        assertArrayEquals(
                new int[]{1, 1, 3, 2, 1, 1, 1},
                new int[]{h, e, l, o, w, r, d});

        assertEquals(7, countersMap.size());
    }

    @Test
    public void countChars_emptyString() throws Exception {
        Map<Character, Integer> countersMap = CharCounter.countChars("");

        assertEquals(0, countersMap.size());
    }

    @Test
    public void countChars_nullString() throws Exception {
        Map<Character, Integer> countersMap = CharCounter.countChars(null);

        assertEquals(0, countersMap.size());
    }
}