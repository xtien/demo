/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.csv;

import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by christine on 11-1-18.
 */

public class MyCsvReaderImpl implements MyCsvReader {

    private final String fileName;
    private final ExecutorService executor;

    public MyCsvReaderImpl(String fileName) {

        this.fileName = fileName;
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public void readIssues(final CsvCallback callback) {

        executor.submit(new Runnable() {

            @Override
            public void run() {

                try {
                    FileReader fr = new FileReader(fileName);
                    String contents = FileUtils.readFileToString(new File(fileName), "UTF-8");
                    contents = contents.replace("\"", "");

                    List<Issue> issues = new ArrayList<Issue>();

                    String[] issueStrings = contents.split("\r\n");

                    for (int i = 1; i < issueStrings.length; i++) {
                        String[] issueString = issueStrings[i].split(",");
                        Issue issue = new Issue(issueString[0], issueString[1], issueString[2], issueString[3]);
                        issues.add(issue);
                    }

                    callback.setResult(issues);

                } catch (FileNotFoundException e) {
                    callback.setException(e);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
