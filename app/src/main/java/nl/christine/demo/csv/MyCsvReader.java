package nl.christine.demo.csv;

import java.io.IOException;

/**
 * Created by christine on 11-1-18.
 */

public interface MyCsvReader {
    void readIssues(CsvCallback callback);
}
