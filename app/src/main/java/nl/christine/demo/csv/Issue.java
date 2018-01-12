package nl.christine.demo.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

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

    @CsvBindByPosition(position = 0)
    private String firstName;

    @CsvBindByPosition(position = 1)
    private String lastName;

    @CsvBindByPosition(position = 2)
    private int number;

    @CsvBindByPosition(position = 3)
    private Date date;

    public Issue(String firstName, String lastName, String number, String date) {
        this.lastName = lastName;
        this.number = Integer.parseInt(number);
        try {
            this.date = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.firstName = firstName;
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

