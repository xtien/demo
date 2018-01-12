/*
 * Copyright (c) 2015 - 2018, Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of
 * the Apache License, Version 2.0. You can find a copy of the license at
 * http://www. apache.org/licenses/LICENSE-2.0.
 */

package nl.christine.demo.log;

import android.util.Log;

import nl.christine.demo.BuildConfig;

/**
 * Created by christine on 13-6-16.
 */
public class MyLog  {

    private static boolean logging = false;

    public MyLog() {

        if (BuildConfig.DEBUG) {
            logging = true;
        } else {
            logging = false;
        }
    }

    public  void i(String tag, String message) {
        if (logging) {
            Log.i(tag, message);
        }
    }

    public   void d(String tag, String message) {
        if (logging) {
            Log.d(tag, message);
        }
    }

    public   void e(String tag, String message) {
        if (logging) {
            Log.e(tag, message);
        }
    }

    public   void e(String tag, Exception e) {
        if (logging) {
            e.printStackTrace();
        }
    }
}
