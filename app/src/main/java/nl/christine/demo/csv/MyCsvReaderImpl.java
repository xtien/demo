package nl.christine.demo.csv;

import android.util.Log;

import com.opencsv.bean.CsvToBeanBuilder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by christine on 11-1-18.
 */

public class MyCsvReaderImpl implements MyCsvReader {

    private final String fileName;
    private final ExecutorService executor;
    private String TAG = getClass().getSimpleName();

    public MyCsvReaderImpl(String fileName) {

        this.fileName = fileName;
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public void readIssues(final CsvCallback callback) {

        executor.submit(new Runnable() {
            @Override
            public void run() {

                try {
                    FileReader fr = new FileReader(fileName);
                    String contents = FileUtils.readFileToString(new File(fileName), "UTF-8");
                    contents = contents.replace("\"","");

                    List<Issue> issues = new ArrayList<Issue>();

                    String[] issueStrings = contents.split("\r\n");

                    for(int i=1;i<issueStrings.length;i++){
                        String[] issueString = issueStrings[i].split(",");
                        Issue issue = new Issue(issueString[0],issueString[1],issueString[2],issueString[3]);
                        issues.add(issue);
                    }

                    Log.d("","");



                    callback.setResult(issues);
                } catch (FileNotFoundException e) {
                    callback.setException(e);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
