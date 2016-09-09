package com.letfar.alphabetspectrum.utils;

import java.util.Locale;
import java.util.Map;

/**
 * Created by Alex on 07.09.2016.
 */
public class StringFormatUtils {
    public static String getFormatted100PercentString(Double percent) {
        return String.format(Locale.getDefault(),
                "%.1f%%",
                percent * 100);
    }

    public static String getFormattedPercentString(Double percent) {
        return String.format(Locale.getDefault(),
                "%.2f",
                percent);
    }

}
