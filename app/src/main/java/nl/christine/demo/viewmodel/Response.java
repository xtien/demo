/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import nl.christine.demo.csv.Issue;

import static nl.christine.demo.viewmodel.Status.ERROR;
import static nl.christine.demo.viewmodel.Status.LOADING;
import static nl.christine.demo.viewmodel.Status.SUCCESS;

/**
 * Response holder provided to the UI
 */
public class Response {

    public final Status status;

    @Nullable
    public final List<Issue> data;

    @Nullable
    public final Throwable error;

    private Response(Status status, @Nullable List<Issue> data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static Response loading() {
        return new Response(LOADING, null, null);
    }

    public static Response success(@NonNull List<Issue> issues) {
        return new Response(SUCCESS, issues, null);
    }

    public static Response error(@NonNull Throwable error) {
        return new Response(ERROR, null, error);
    }
}
