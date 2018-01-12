/*
 * Copyright (c) 2018, Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of
 * the Apache License, Version 2.0. You can find a copy of the license at
 * http://www. apache.org/licenses/LICENSE-2.0.
 */

package nl.christine.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nl.christine.demo.log.LogFactory;
import nl.christine.demo.log.MyLog;
import nl.christine.demo.dialog.MyAlert;


/**
 * Created by christine
 */
public class BaseActivity extends AppCompatActivity {

    private static final String LOGTAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   }

    protected void showAlert(final Exception e) {
        LogFactory.get().e(LOGTAG, e);
        showAlert(e.getClass().getSimpleName() + " " + (e.getMessage() != null ? e.getMessage() : ""));
    }

    protected void showAlert(int statusCode, String message) {
        showAlert(statusCode + " " + message);
    }

    protected void showAlert(final String text) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                MyAlert alert = new MyAlert(BaseActivity.this);
                alert.setText(text);
                alert.show();
            }
        });
    }
}
