package com.letfar.alphabetspectrum.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import com.letfar.alphabetspectrum.R;
import com.letfar.alphabetspectrum.activity.View.HistogramView;
import com.letfar.alphabetspectrum.utils.ViewUtils;

/**
 * Created by Alex on 11.09.2016.
 */
public class TextinfoFragment extends Fragment {
    private FileListActivity activity;

    // Text preview
    private TextView textViewTextPreview;

    // Histogram
    private HistogramView histogram;

    // Percent table
    private TableLayout alphabetTable;
    private ImageView alphabetTableTitlePercent;

    private boolean isTableInPercents = true;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_textinfo, container, false);

        this.activity = (FileListActivity) getActivity();

        // Init views
        this.textViewTextPreview = (TextView) view.findViewById(R.id.textPreview);
        this.histogram = (HistogramView) view.findViewById(R.id.histogram);
        this.alphabetTable = (TableLayout) view.findViewById(R.id.alphabet_table);
        this.alphabetTableTitlePercent = (ImageView) view.findViewById(R.id.alphabet_table_title_percent);

        // Init OnClick
        alphabetTableTitlePercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity.percentCharsMap == null) return;
                isTableInPercents = !isTableInPercents;
                ViewUtils.fillAlphabetTable(alphabetTable, activity.percentCharsMap, isTableInPercents);
            }
        });

        analyzeTextAndShowResults();

        return view;
    }

    private void analyzeTextAndShowResults() {
        if (activity.percentCharsMap == null || activity.percentCharsMap.isEmpty())
            return;

        // Show text preview
        textViewTextPreview.setText(activity.textToAnalyze);
        // Fill table
        ViewUtils.fillAlphabetTable(alphabetTable, activity.percentCharsMap, isTableInPercents);

        // Update histogram
        histogram.setPercentrage(activity.percentCharsMap);
        histogram.postInvalidate();
    }
}
