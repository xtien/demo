/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.dagger;

import android.content.Context;
import android.support.annotation.Nullable;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nl.christine.demo.App;
import nl.christine.demo.csv.Issue;
import nl.christine.demo.csv.MyCsvReader;
import nl.christine.demo.csv.MyCsvReaderImpl;
import nl.christine.demo.dao.DemoDatabaseHelper;
import nl.christine.demo.dao.IssueDao;

/**
 * This is where you will inject application-wide dependencies.
 */
@Module
public class AppModule {

    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    MyCsvReader provideMyCsvReader(Context context, DemoDatabaseHelper databaseHelper) {
        return new MyCsvReaderImpl(context, databaseHelper);
    }

    @Singleton
    @Provides
    DemoDatabaseHelper provideDatabaseHelper(Context context) {
            return  new DemoDatabaseHelper(context);
     }
}
