/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.activity;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import nl.christine.demo.R;
import nl.christine.demo.csv.Issue;
import nl.christine.demo.list.CsvListAdapter;
import nl.christine.demo.viewmodel.MainActivityViewModel;
import nl.christine.demo.viewmodel.Response;
import nl.christine.demo.viewmodel.MainActivityViewModelFactory;

public class MainActivity extends BaseActivity  implements HasActivityInjector {

    @Inject
    public DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    private RecyclerView listView;

    private static final int MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 1001;
    private LinearLayoutManager layoutManager;

    @Inject
    public MainActivityViewModelFactory mainActivityViewModelFactory;

    private MainActivityViewModel viewModel;
    private View progressBar;

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
        AndroidInjection.inject(this);
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
        progressBar = findViewById(R.id.progress_bar);

        layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);

        viewModel = ViewModelProviders.of(this, mainActivityViewModelFactory).get(MainActivityViewModel.class);

        viewModel.response().observe(this, response -> processResponse(response));

        viewModel.loadIssues();
    }

    private void processResponse(Response response) {

        switch (response.status) {
            case LOADING:
                renderLoadingState();
                break;

            case SUCCESS:
                renderDataState(response.data);
                break;

            case ERROR:
                renderErrorState(response.error);
                break;
        }
    }

    private void renderLoadingState() {
        listView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void renderDataState(List<Issue> issues) {
        listView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        listView.setAdapter(new CsvListAdapter(getApplicationContext(), issues));
    }

    private void renderErrorState(Throwable throwable) {
        showAlert(throwable);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
