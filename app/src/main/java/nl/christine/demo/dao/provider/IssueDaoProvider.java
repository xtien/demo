/*
 * Copyright (c) 2015 - 2016, Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of
 * the Apache License, Version 2.0. You can find a copy of the license at
 * http://www. apache.org/licenses/LICENSE-2.0.
 */

package nl.christine.demo.dao.provider;

import java.sql.SQLException;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Provider;

import nl.christine.demo.csv.Issue;
import nl.christine.demo.dao.DemoDatabaseHelper;
import nl.christine.demo.dao.IssueDao;

/**
 * created by Christine
 * utility class for injecting a dao class
 */
public class IssueDaoProvider implements Provider<IssueDao> {

    protected DemoDatabaseHelper helper;

    @Inject
    public IssueDaoProvider(DemoDatabaseHelper helper){
        this.helper = helper;
    }

    @Override
    public IssueDao get() {

        IssueDao dao = (IssueDao) helper.getDAO(Issue.class);

        return dao;
    }
}
