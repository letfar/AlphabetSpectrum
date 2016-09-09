package com.letfar.alphabetspectrum.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Alex on 03.09.2016.
 */
public class FileUtils {

    public static void showFileChooser(Activity activity, int fileSelectRequestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            activity.startActivityForResult(
                    Intent.createChooser(intent, "Виберіть текстовий файл для аналізу"),
                    fileSelectRequestCode);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(activity,
                    "Спочатку встановіть файловий менеджер",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public static String readTextFromUri(Uri uri, Activity activity) {
        try {
            InputStream inputStream = activity.getContentResolver().openInputStream(uri);
            return getTextFromInputStream(inputStream, 2000);

        } catch (Exception ex) {
            Toast.makeText(activity.getApplicationContext(),
                    "Problem with opening file. " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        return "";
    }

    public static String getTextFromInputStream(InputStream inputStream, int resultStreamSizeLimit) throws IOException {
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();

        try {
            boolean hasLimit = resultStreamSizeLimit > 0;
            byte[] buffer = new byte[512];
            int readLength;

            while ((readLength = inputStream.read(buffer)) != -1) {

                resultStream.write(buffer, 0, readLength);

                if (hasLimit && resultStream.size() > resultStreamSizeLimit)
                    break;
            }

        } finally {
            inputStream.close();
            resultStream.close();
        }

        return resultStream.toString("UTF-8");
    }
}
