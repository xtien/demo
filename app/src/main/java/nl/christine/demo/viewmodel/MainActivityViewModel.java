/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import nl.christine.demo.csv.Issue;
import nl.christine.demo.csv.MyCsvReader;
import nl.christine.demo.rx.SchedulersFacade;

/**
 * Created by christine on 31-1-18.
 */

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    public MyCsvReader reader;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public final MutableLiveData<Response> response = new MutableLiveData<>();
    private Single<Object> issuesSingle;

    private final SchedulersFacade schedulersFacade;

    public MainActivityViewModel(MyCsvReader myCsvReader, SchedulersFacade schedulersFacade) {
        this.reader = myCsvReader;
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

    public MutableLiveData<Response> response() {

        return response;
    }

    public void loadIssues() {

        Log.d(TAG, "loadIssues");

        issuesSingle = Single.create(emitter -> {

            Thread thread = new Thread(() -> {
                try {
                    Log.d(TAG, "load issues runnable");
                    List<Issue> issues = reader.readIssues();
                    emitter.onSuccess(issues);
                } catch (Exception e) {
                    emitter.onError(e);
                }
            });
            Log.d(TAG, "thread.start");
            thread.start();
        });

        disposables.add(issuesSingle.subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .subscribe(issue -> response.setValue(Response.success((List<Issue>) issue))));
    }

}
