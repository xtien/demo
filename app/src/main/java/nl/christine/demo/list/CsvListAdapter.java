/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */

package nl.christine.demo.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nl.christine.demo.R;
import nl.christine.demo.csv.Issue;

public class CsvListAdapter extends RecyclerView.Adapter<CsvListAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    List<Issue> issues = new ArrayList<Issue>();

    public CsvListAdapter(Context context, List<Issue> issues) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.issues = issues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.csv_list_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Issue issue = issues.get(position);

        holder.name.setText(issue.getName());
        holder.numberOfIssues.setText(Integer.toString(issue.getNumber()));
        holder.date.setText(issue.getDate());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return issues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, numberOfIssues, date;

        public ViewHolder(LinearLayout v) {
            super(v);

            name = (TextView) v.findViewById(R.id.name);
            numberOfIssues = (TextView) v.findViewById(R.id.number_of_issues);
            date = (TextView) v.findViewById(R.id.date);
        }
    }
}
