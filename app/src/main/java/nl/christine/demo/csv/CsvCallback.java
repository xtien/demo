/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */

package nl.christine.demo.csv;

import java.util.List;

/**
 * Created by christine on 11-1-18.
 */

public interface CsvCallback {

    void setResult(List<Issue> result);

    void setException(Exception e);
}
