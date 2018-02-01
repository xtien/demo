/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.csv;

import android.content.Context;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import nl.christine.demo.R;

/**
 * Created by christine on 11-1-18.
 */

public class MyCsvReaderImpl implements MyCsvReader {

    private  String fileName;
    private final ExecutorService executor;

    @Inject
    public Context context;

    public MyCsvReaderImpl() {

        this.fileName = "/sdcard/issues.csv";
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public List<Issue> readIssues() {

        try {
            String contents = FileUtils.readFileToString(new File(fileName), "UTF-8");
            contents = contents.replace("\"", "");

            List<Issue> issues = new ArrayList<Issue>();

            String[] issueStrings = contents.split("\r\n");

            for (int i = 1; i < issueStrings.length; i++) {
                String[] issueString = issueStrings[i].split(",");
                Issue issue = new Issue(issueString[0], issueString[1], issueString[2], issueString[3]);
                issues.add(issue);
            }

            return issues;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<Issue>();
    }
}
