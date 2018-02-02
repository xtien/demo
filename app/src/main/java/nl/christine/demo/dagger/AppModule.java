/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.dagger;

import android.content.Context;

import java.sql.SQLException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nl.christine.demo.App;
import nl.christine.demo.csv.Issue;
import nl.christine.demo.csv.MyCsvReader;
import nl.christine.demo.csv.MyCsvReaderImpl;
import nl.christine.demo.dao.DemoDatabaseHelper;
import nl.christine.demo.dao.IssueDao;
import nl.christine.demo.dao.impl.IssueDaoImpl;

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
    MyCsvReader provideMyCsvReader(Context context) {
        return new MyCsvReaderImpl(context);
    }

//    @Singleton
//    @Provides
//    IssueDao provideIssueDao(Context context) throws SQLException {
//        return (IssueDao) new DemoDatabaseHelper(context).getDAO(Issue.class);
//    }
}
