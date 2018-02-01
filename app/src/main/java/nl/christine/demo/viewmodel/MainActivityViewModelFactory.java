/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import nl.christine.demo.csv.MyCsvReader;
import nl.christine.demo.rx.SchedulersFacade;

/**
 * Created by christine on 31-1-18.
 */

public class MainActivityViewModelFactory implements ViewModelProvider.Factory{

    private final MyCsvReader myCvsReader;
    private final SchedulersFacade schedulersFacade;

    public MainActivityViewModelFactory(MyCsvReader myCsvReader, SchedulersFacade schedulersFacade) {
        this.myCvsReader = myCsvReader;
        this.schedulersFacade = schedulersFacade;
     }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            return (T) new MainActivityViewModel(myCvsReader, schedulersFacade);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
