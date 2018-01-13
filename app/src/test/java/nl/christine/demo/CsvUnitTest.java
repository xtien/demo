/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */

package nl.christine.demo;

import android.content.Context;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import nl.christine.demo.csv.CsvCallback;
import nl.christine.demo.csv.Issue;
import nl.christine.demo.csv.MyCsvReader;
import nl.christine.demo.csv.MyCsvReaderFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test copying csv file from assets, and reading issues from the file.
 */
@RunWith(RobolectricTestRunner.class)
public class CsvUnitTest {

    private boolean done;
    List<Issue> issues = null;

    @Test
    public void readFile() throws IOException, InterruptedException {

        Context context = ShadowApplication.getInstance().getApplicationContext();

        String issuesFileName = context.getString(R.string.issues_file);

        IOUtils.copy(context.getAssets().open(issuesFileName), new FileOutputStream(issuesFileName));

        MyCsvReader reader = MyCsvReaderFactory.get(issuesFileName);
        assertNotNull(reader);

        reader.readIssues(new CsvCallback() {

            @Override
            public void setResult(List<Issue> result) {
                done = true;
                issues = result;
            }

            @Override
            public void setException(Exception e) {
                done = true;
            }
        });

        while (!done) {
            Thread.sleep(10l);
        }

        assertEquals(3, issues.size());
    }
}