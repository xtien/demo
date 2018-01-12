package nl.christine.demo.csv;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by christine on 11-1-18.
 */

public interface CsvCallback {

    void setResult(List<Issue> result);

    void setException(Exception e);
}
