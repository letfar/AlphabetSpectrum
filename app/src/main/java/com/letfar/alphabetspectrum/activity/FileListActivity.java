package com.letfar.alphabetspectrum.activity;

import android.content.*;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;
import com.letfar.alphabetspectrum.*;
import com.letfar.alphabetspectrum.activity.View.HistogramView;
import com.letfar.alphabetspectrum.exception.ClipboardException;
import com.letfar.alphabetspectrum.utils.AlgorithmUtils;
import com.letfar.alphabetspectrum.utils.FileUtils;
import com.letfar.alphabetspectrum.utils.ViewUtils;

import java.util.Map;

import static com.letfar.alphabetspectrum.utils.ClipboardUtils.getClipboardText;
import static com.letfar.alphabetspectrum.utils.ExceptionUtils.toastException;
import static java.lang.Double.valueOf;
import static java.lang.Math.round;

public class FileListActivity extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 0;

    private ViewGroup layoutContent;
    private ViewGroup layoutTextPreview;
    private TextView textViewTextPreview;
    private TextView textViewEmptyListTip;
    private TableLayout alphabetTable;
    private ImageView alphabetTableTitlePercent;
    private HistogramView histogram;
    private FloatingActionButton floatingButtonSelectFile;
    private FloatingActionButton floatingButtonClipboard;
    private boolean showTableInPercent;

    private Map<Character, Double> percentCharsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_list);

        this.layoutContent = (ViewGroup) findViewById(R.id.content_layout);
        this.textViewTextPreview = (TextView) findViewById(R.id.textPreview);
        this.layoutTextPreview = (LinearLayout) findViewById(R.id.textPreviewLayout);
        this.floatingButtonSelectFile = (FloatingActionButton) findViewById(R.id.fab_right);
        this.floatingButtonClipboard = (FloatingActionButton) findViewById(R.id.fab_left);
        this.textViewEmptyListTip = (TextView) findViewById(R.id.empty_list_tip);
        this.alphabetTable = (TableLayout) findViewById(R.id.alphabet_table);
        this.histogram = (HistogramView) findViewById(R.id.hystogram);

        this.alphabetTable.setVisibility(View.GONE);
        this.histogram.setVisibility(View.GONE);
        this.layoutTextPreview.setVisibility(View.GONE);
        this.alphabetTableTitlePercent = (ImageView) findViewById(R.id.percent_table_header);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        floatingButtonSelectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileUtils.showFileChooser(FileListActivity.this, FILE_SELECT_CODE);
            }
        });

        floatingButtonClipboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    findAndShowAlphabetSpectrum(getClipboardText(getApplicationContext()));
                } catch (ClipboardException ex) {
                    toastException(ex, getApplicationContext());
                }
            }
        });

        alphabetTableTitlePercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (percentCharsMap != null) {
                    showTableInPercent = !showTableInPercent;
                    ViewUtils.fillAlphabetTable(alphabetTable, percentCharsMap, showTableInPercent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_file_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_about) {
            startActivity(new Intent(getApplicationContext(), AboutActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    getSelectedFileText(data);
                }
                break;
        }
    }

    private void getSelectedFileText(Intent data) {
        Uri uri = data.getData();
        String text = FileUtils.readTextFromUri(uri, FileListActivity.this);

        if (text == null || text.isEmpty()) return;

        findAndShowAlphabetSpectrum(text);
    }

    private void findAndShowAlphabetSpectrum(String text) {
        if (text == null || text.isEmpty()) return;

        // Find alphabet chars spectrum
        this.percentCharsMap = AlgorithmUtils.findSpectrum(text);

        // Hide tips
        textViewEmptyListTip.setVisibility(View.GONE);

        // Show text preview
        layoutTextPreview.setVisibility(View.VISIBLE);
        textViewTextPreview.setText(text);

        // Show Histogram
        histogram.setVisibility(View.VISIBLE);
        histogram.setPercentrage(percentCharsMap);
        histogram.postInvalidate();

        // Show alphabetTable
        showTableInPercent = true;
        ViewUtils.fillAlphabetTable(alphabetTable, percentCharsMap, showTableInPercent);
        alphabetTable.setVisibility(View.VISIBLE);
    }

}
