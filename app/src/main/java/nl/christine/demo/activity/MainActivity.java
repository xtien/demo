/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import nl.christine.demo.R;
import nl.christine.demo.csv.CsvCallback;
import nl.christine.demo.csv.Issue;
import nl.christine.demo.csv.MyCsvReader;
import nl.christine.demo.csv.MyCsvReaderFactory;
import nl.christine.demo.list.CsvListAdapter;

public class MainActivity extends BaseActivity {

    private ListView listView;

    private static final int MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 1001;

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case MY_PERMISSIONS_REQUEST_WRITE_STORAGE: {

                 if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    doIt();

                } else {
                    showAlert(getString(R.string.no_permissions));
                }
                return;
            }
        }

        doIt();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_STORAGE);
        } else {
            doIt();
        }
    }

    private void doIt() {
        String issuesFileName = "/sdcard/" + getString(R.string.issues_file);
        File file = new File(issuesFileName);

        if (!file.exists()) {
            try {
                IOUtils.copy(getAssets().open(getString(R.string.issues_file)), new FileOutputStream(issuesFileName));
            } catch (IOException e) {
                showAlert(e);
            }
        }

        listView = findViewById(R.id.list);
        MyCsvReader reader = MyCsvReaderFactory.get(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.issues_file));

        reader.readIssues(new CsvCallback() {

            @Override
            public void setResult(final List<Issue> result) {

                if (result != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listView.setAdapter(new CsvListAdapter(getApplicationContext(), result));
                        }
                    });
                }
            }

            @Override
            public void setException(Exception e) {
                showAlert(e);
            }
        });
    }
}
