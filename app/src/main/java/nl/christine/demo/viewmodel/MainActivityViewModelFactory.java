/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import nl.christine.demo.rx.SchedulersFacade;

/**
 * Created by christine on 31-1-18.
 */

public class MainActivityViewModelFactory implements ViewModelProvider.Factory{


    private final SchedulersFacade schedulersFacade;

    public MainActivityViewModelFactory(SchedulersFacade schedulersFacade) {
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            return (T) new MainActivityViewModel(schedulersFacade);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
