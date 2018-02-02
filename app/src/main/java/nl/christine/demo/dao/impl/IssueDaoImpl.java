package nl.christine.demo.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import nl.christine.demo.csv.Issue;
import nl.christine.demo.dao.IssueDao;


public class IssueDaoImpl extends BaseDaoImpl<Issue, Integer> implements IssueDao {

    private static final String LOGTAG = IssueDaoImpl.class.getSimpleName();

    public IssueDaoImpl(ConnectionSource connectionSource, Class<Issue> dataClass) throws SQLException {
        super(connectionSource, Issue.class);
    }

    @Override
    public Issue store(Issue issue) throws SQLException {


        return issue;
    }

    @Override
    public List<Issue> getIssues() throws SQLException {
        return queryForAll();
    }
}
