package com.example.quanlyluong.Library;

import android.app.Activity;
import android.widget.EditText;

public class CheckError {
    Activity activity;

    public CheckError(Activity myActivity) {
        activity = myActivity;
    }

    public void checkEmpty(EditText check, String warning) {
        if (check.getText().toString().isEmpty()) {
            check.setError(warning);
            check.isFocused();
        }
    }
}
