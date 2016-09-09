package com.letfar.alphabetspectrum.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Alex on 07.09.2016.
 */
public class ExceptionUtils {
    public static void toastException(Exception ex, Context context) {
        Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
