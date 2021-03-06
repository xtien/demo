/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */
package nl.christine.demo.csv;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by christine on 11-1-18.
 */

public class Issue {

    public static FastDateFormat dateFormat = FastDateFormat.getInstance("dd MMM HH:mm");
    static FastDateFormat df = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss");

    private String firstName;

    private String lastName;

    private int number = 0;

    private Date date;

    public Issue(String firstName, String lastName, String number, String date) {

        this.firstName = firstName;
        this.lastName = lastName;

        if (NumberUtils.isDigits(number)) {
            this.number = Integer.parseInt(number);
        }

        try {
            this.date = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            this.date = new Date(System.currentTimeMillis());
        }
    }

    public int getNumber() {
        return number;
    }

    public String getDate() {
        return dateFormat.format(date);
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}

