/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */

package nl.christine.demo.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nl.christine.demo.R;
import nl.christine.demo.csv.Issue;

public class CsvListAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    List<Issue> issues = new ArrayList<Issue>();

    public CsvListAdapter(Context context, List<Issue> issues) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.issues = issues;
    }

    @Override
    public int getCount() {
        return issues.size();
    }

    @Override
    public Object getItem(int position) {
        return issues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Issue issue = issues.get(position);

        convertView = inflater.inflate(R.layout.csv_list_row, null);
        ((TextView) convertView.findViewById(R.id.name)).setText(issue.getName());
        ((TextView) convertView.findViewById(R.id.number_of_issues)).setText(Integer.toString(issue.getNumber()));
        ((TextView) convertView.findViewById(R.id.date)).setText(issue.getDate());

        return convertView;
    }
}
