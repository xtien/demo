/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.activity;

import dagger.Module;
import dagger.Provides;
import nl.christine.demo.csv.MyCsvReader;
import nl.christine.demo.rx.SchedulersFacade;
import nl.christine.demo.viewmodel.MainActivityViewModelFactory;

@Module
public class MainActivityModule {

    @Provides
    MainActivityViewModelFactory provideMainActivityViewModelFactory(MyCsvReader myCsvReader, SchedulersFacade schedulersFacade) {
        return new MainActivityViewModelFactory(myCsvReader, schedulersFacade);
    }
}
