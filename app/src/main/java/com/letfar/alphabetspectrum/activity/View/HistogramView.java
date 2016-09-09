package com.letfar.alphabetspectrum.activity.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Collections;
import java.util.Map;

import static android.graphics.Color.rgb;
import static com.letfar.alphabetspectrum.algorithm.AlphabetCharCounter.EN_LOW_ALPHABET;
import static java.lang.String.valueOf;

/**
 * Created by Alex on 03.09.2016.
 */
public class HistogramView extends View {
    private Paint paint = new Paint();
    private Map<Character, Double> percentrage;

    private float yCharPos;
    private float xCharPos;
    private float xCharSpace;

    private float xHistPos;
    private float yHistPos;

    private Canvas canvas;

    public void setPercentrage(Map<Character, Double> percentrage) {
        this.percentrage = percentrage;
    }

    public HistogramView(Context context) {
        super(context);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        float h = getMeasuredHeight();
        float w = getMeasuredWidth();

        // Fill background
        canvas.drawRGB(240, 240, 240);

        paint.setColor(Color.BLACK);
        int fontSize = 18;
        paint.setAntiAlias(true);
        paint.setTextSize(fontSize);
        paint.setTypeface(Typeface.MONOSPACE);

        xCharPos = 0;
        yCharPos = h - fontSize;
        xCharSpace = 15;

        xHistPos = xCharPos + fontSize / (float) 2;
        yHistPos = yCharPos - fontSize;

        for (char character : EN_LOW_ALPHABET.toCharArray()) {
            canvas.drawText(valueOf(character), xCharPos, yCharPos, paint);
            xCharPos += xCharSpace;
        }

        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);

        drawHistogram(percentrage, rgb(206, 75, 38));
    }

    public void drawHistogram(Map<Character, Double> percentrage, int color) {
        paint.setColor(color);

        double maxPercent = Collections.max(percentrage.values());
        double columnMaxHeight = yHistPos * maxPercent;
        double scale = yHistPos / columnMaxHeight;

        for (char character : EN_LOW_ALPHABET.toCharArray()) {
            float yHistEndPos = (this.percentrage != null)
                    ? yHistPos - (float) (yHistPos * this.percentrage.get(character) * scale)
                    : 0f;

            canvas.drawLine(xHistPos, yHistPos, xHistPos, yHistEndPos, paint);
            xHistPos += xCharSpace;
        }
    }
}
