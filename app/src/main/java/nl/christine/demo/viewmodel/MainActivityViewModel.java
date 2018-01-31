/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import nl.christine.demo.csv.Issue;
import nl.christine.demo.csv.MyCsvReader;
import nl.christine.demo.rx.SchedulersFacade;

/**
 * Created by christine on 31-1-18.
 */

public class MainActivityViewModel extends ViewModel {

    private final SchedulersFacade schedulersFacade;

    @Inject
    public MyCsvReader reader;

    private final CompositeDisposable disposables = new CompositeDisposable();

    public final MutableLiveData<Response> response = new MutableLiveData<>();

    public MainActivityViewModel(SchedulersFacade schedulersFacade) {
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

        Observable.create(new ObservableOnSubscribe<Issue>() {

            @Override
            public void subscribe(ObservableEmitter<Issue> emitter) throws Exception {

                try {

                    List<Issue> issues = reader.readIssues();
                    for (Issue issue : issues) {
                        emitter.onNext(issue);
                    }
                    emitter.onComplete();

                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }

}
