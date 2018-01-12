package nl.christine.demo.csv;

import android.content.Context;

import nl.christine.demo.R;

/**
 * Created by christine on 11-1-18.
 */

public class MyCsvReaderFactory {

    private static MyCsvReader instance;

    public static MyCsvReader get(String fileName) {

        if (instance == null) {
            instance = new MyCsvReaderImpl(fileName);
        }

        return instance;
    }
}
