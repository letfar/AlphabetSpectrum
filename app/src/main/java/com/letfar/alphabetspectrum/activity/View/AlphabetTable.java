package com.letfar.alphabetspectrum.activity.View;

import android.content.Context;
import android.graphics.Color;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.letfar.alphabetspectrum.utils.ColorUtills;
import com.letfar.alphabetspectrum.utils.StringFormatUtils;

import java.util.Locale;

import static java.lang.String.valueOf;

/**
 * Created by Alex on 07.09.2016.
 */
public interface AlphabetTable {
    class Row extends TableRow {

        private class RowText extends TextView {
            RowText(Context context, String text) {
                super(context);
                setText(text);
                setTextColor(Color.BLACK);
            }
        }

        public Row(TableLayout table, String alphabetChar, Double alphabetCharPercent, boolean showAsPercent) {
            super(table.getContext());
            addView(new RowText(table.getContext(), alphabetChar));
            addView(new RowText(table.getContext(), (showAsPercent)
                    ? StringFormatUtils.getFormatted100PercentString(alphabetCharPercent)
                    : StringFormatUtils.getFormattedPercentString(alphabetCharPercent)));

            setPadding(10, 10, 10, 10);
            setBackgroundColor((table.getChildCount() % 2 == 0)
                    ? ColorUtills.TABLE_ROW1
                    : ColorUtills.TABLE_ROW2);
        }
    }
}
