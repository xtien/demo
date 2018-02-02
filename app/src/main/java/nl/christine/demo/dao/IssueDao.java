package nl.christine.demo.dao;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import nl.christine.demo.csv.Issue;

public interface IssueDao extends Dao<Issue, Integer> {

    Issue store(Issue rider) throws SQLException;

    List<Issue> getIssues() throws SQLException;

}
