package com.letfar.alphabetspectrum;

import com.letfar.alphabetspectrum.algorithm.AlphabetCharCounter;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static com.letfar.alphabetspectrum.algorithm.AlphabetCharCounter.EN_LOW_ALPHABET;
import static org.junit.Assert.*;

/**
 * Created by Alex on 03.09.2016.
 */
public class AlphabetCharCounterTest {
    @Test
    public void countChars() throws Exception {
        Map<Character, Integer> countersMap = AlphabetCharCounter.countAlphabetChars("HelloWorld");

        assertEquals(EN_LOW_ALPHABET.length(), countersMap.size());

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


        int a = countersMap.get('a');
        int b = countersMap.get('b');
        int c = countersMap.get('c');

        assertArrayEquals(
                new int[]{0, 0, 0},
                new int[]{a, b, c});
    }

    @Test
    public void toString_test() throws Exception {
        Map<Character, Integer> countersMap = AlphabetCharCounter.countAlphabetChars("HelloWorld");

        assertEquals(
                "a[0] b[0] c[0] d[1] e[1] f[0] g[0] h[1] i[0] j[0] k[0] l[3] " +
                        "m[0] n[0] o[2] p[0] q[0] r[1] s[0] t[0] u[0] v[0] w[1] x[0] y[0] z[0]",
                AlphabetCharCounter.toString(countersMap));
    }


    @Test
    public void asPercents_test() throws Exception {
        Map<Character, Integer> countersMap = AlphabetCharCounter.countAlphabetChars("aaabbb");

        Map<Character, Double> percentMap = AlphabetCharCounter.asPercents(countersMap);
        assertEquals(
                "a[0.5] b[0.5] c[0.0] d[0.0] e[0.0] f[0.0] g[0.0] h[0.0] i[0.0] j[0.0] k[0.0] l[0.0] " +
                        "m[0.0] n[0.0] o[0.0] p[0.0] q[0.0] r[0.0] s[0.0] t[0.0] u[0.0] v[0.0] w[0.0] x[0.0] y[0.0] z[0.0]",
                AlphabetCharCounter.toString(percentMap));

        // Percent sum must be equal to 1
        float sum = 0;
        for (Double d : percentMap.values())
            sum += d;

        assertEquals(1f, sum, 0.00001);
    }

}