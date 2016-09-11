package com.letfar.alphabetspectrum.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import com.letfar.alphabetspectrum.R;
import com.letfar.alphabetspectrum.activity.View.HistogramView;
import com.letfar.alphabetspectrum.exception.ClipboardException;
import com.letfar.alphabetspectrum.utils.AlgorithmUtils;
import com.letfar.alphabetspectrum.utils.FileUtils;
import com.letfar.alphabetspectrum.utils.ViewUtils;

import java.util.Map;

import static com.letfar.alphabetspectrum.utils.ClipboardUtils.getClipboardText;
import static com.letfar.alphabetspectrum.utils.ExceptionUtils.toastException;

/**
 * Created by Alex on 07.09.2016.
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
    }
}
