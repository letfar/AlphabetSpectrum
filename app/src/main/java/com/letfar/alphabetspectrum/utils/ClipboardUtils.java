package com.letfar.alphabetspectrum.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;
import com.letfar.alphabetspectrum.activity.FileListActivity;
import com.letfar.alphabetspectrum.exception.ClipboardException;

import static android.content.ClipDescription.MIMETYPE_TEXT_HTML;
import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

/**
 * Created by Alex on 07.09.2016.
 */
public class ClipboardUtils {
    public static String getClipboardText(Context context) {
        ClipboardManager clipboard = (ClipboardManager)
                context.getSystemService(Context.CLIPBOARD_SERVICE);

        if (clipboard.hasPrimaryClip()) {
            ClipDescription clipDescr = clipboard.getPrimaryClipDescription();
            boolean hasPlainText = clipDescr.hasMimeType(MIMETYPE_TEXT_PLAIN)
                    || clipDescr.hasMimeType(MIMETYPE_TEXT_HTML);

            if (hasPlainText) {
                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                return item.getText().toString();
            } else
                throw new ClipboardException("Буфер не містить простого тексту.");

        } else
            throw new ClipboardException("Буфер порожній");
    }
}