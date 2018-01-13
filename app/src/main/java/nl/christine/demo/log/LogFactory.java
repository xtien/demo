/*
 * Copyright (c) 2018. Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of the Apache License, Version 2.0. You can find a copy of the license at  http://www. apache.org/licenses/LICENSE-2.0.
 */

package nl.christine.demo.log;

/**
 * Created by christine on 11-1-18.
 */

public class LogFactory {

    private static MyLog log;

    public static MyLog get(){
        if(log == null){
            log = new MyLog();
        }
        return log;
    }
}
