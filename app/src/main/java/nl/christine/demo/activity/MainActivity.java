/*
 * Copyright (c) 2018, Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of
 * the Apache License, Version 2.0. You can find a copy of the license at
 * http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.activity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.apache.commons.io.IOUtils;

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
    private String firstRun = "first_run";

    private static final int MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        showAlert(getResources().getString(R.string.storage_permission_text));
                    }
                });

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_STORAGE);
            }
        }

        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(firstRun, true)) {
            String issuesFileName = "/sdcard/" + getString(R.string.issues_file);
            try {
                IOUtils.copy(getAssets().open(getString(R.string.issues_file)), new FileOutputStream(issuesFileName));
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
                edit.putBoolean(firstRun, false);
                edit.commit();
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

            }
        });


    }
}
