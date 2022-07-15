package com.example.hiddenbeyondofficial.util;

import android.view.View;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

public class Utility {
    public static class Notice {
        public static void snack(View view, String data) {
            Snackbar snackbar = Snackbar.make(view, data, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
    }
}
