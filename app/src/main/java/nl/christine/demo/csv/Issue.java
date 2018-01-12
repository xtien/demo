package nl.christine.demo.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import org.apache.commons.lang3.math.NumberUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by christine on 11-1-18.
 */

public class Issue {

    public static DateFormat dateFormat = new SimpleDateFormat("dd MMM HH:mm");
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumber() {
        return number;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}

