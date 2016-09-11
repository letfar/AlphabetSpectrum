package com.letfar.alphabetspectrum.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.letfar.alphabetspectrum.R;
import com.letfar.alphabetspectrum.exception.ClipboardException;
import com.letfar.alphabetspectrum.utils.AlgorithmUtils;
import com.letfar.alphabetspectrum.utils.FileUtils;

import java.util.Map;

import static com.letfar.alphabetspectrum.utils.ClipboardUtils.getClipboardText;
import static com.letfar.alphabetspectrum.utils.ExceptionUtils.toastException;

public class FileListActivity extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 0;

    private ViewGroup layoutContent;
    private FloatingActionButton floatingButtonSelectFile;
    private FloatingActionButton floatingButtonClipboard;

    String textToAnalyze = "";
    Map<Character, Double> percentCharsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_list);

        this.layoutContent = (ViewGroup) findViewById(R.id.layout_content);
        this.floatingButtonSelectFile = (FloatingActionButton) findViewById(R.id.fab_right);
        this.floatingButtonClipboard = (FloatingActionButton) findViewById(R.id.fab_left);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Floating buttons
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
                    textToAnalyze = getClipboardText(getApplicationContext());
                    findSpectrumAndShowResults();
                } catch (ClipboardException ex) {
                    toastException(ex, getApplicationContext());
                }
            }
        });
    }

    private void findSpectrumAndShowResults() {
        findAlphabetSpectrum(this.textToAnalyze);
        showAlphabetSpectrumResults();
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
                    this.textToAnalyze = FileUtils.readTextFromUri(data.getData(), FileListActivity.this);
                    findSpectrumAndShowResults();
                }
                break;
        }
    }


    private void findAlphabetSpectrum(String textToAnalyze) {
        if (textToAnalyze == null || textToAnalyze.isEmpty()) return;

        // Find alphabet chars spectrum
        this.percentCharsMap = AlgorithmUtils.findSpectrum(textToAnalyze);
    }

    private void showAlphabetSpectrumResults() {
        layoutContent.removeAllViewsInLayout();

        getFragmentManager()
                .beginTransaction()
                .add(R.id.layout_content, new TextinfoFragment())
                .commit();
    }

}
