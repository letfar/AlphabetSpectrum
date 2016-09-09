package com.letfar.alphabetspectrum.utils;

import android.widget.TableLayout;
import com.letfar.alphabetspectrum.activity.View.AlphabetTable;

import java.util.Map;

/**
 * Created by Alex on 07.09.2016.
 */
public class ViewUtils {
    public static void fillAlphabetTable(TableLayout alphabetTable, Map<Character, Double> percentCharsMap, boolean showAsPercent) {
        // Clean table except headers
        if (alphabetTable.getChildCount() > 1)
            alphabetTable.removeViews(1, alphabetTable.getChildCount() - 1);

        // Add rows with alphabet
        for (final Map.Entry<Character, Double> entry : percentCharsMap.entrySet()) {

            alphabetTable.addView(new AlphabetTable.Row(
                    alphabetTable,
                    entry.getKey().toString(), // char
                    entry.getValue(),          // percent
                    showAsPercent));
        }
    }
}
