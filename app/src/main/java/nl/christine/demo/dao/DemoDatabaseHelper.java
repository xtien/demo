package nl.christine.demo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import nl.christine.demo.Constants;
import nl.christine.demo.csv.Issue;
import nl.christine.demo.dao.impl.IssueDaoImpl;

/**
 * Database helper class used to manage the creation and upgrading of your
 * database. This class also usually provides the DAOs used by the other
 * classes.
 */
public class DemoDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = Constants.DATABASE_NAME;
    private static final int DATABASE_VERSION = Constants.DATABASE_VERSION;
    private static final String TAG = DemoDatabaseHelper.class.getSimpleName();

    private final String LOGTAG = getClass().getSimpleName();
    private final Collection<Class<?>> classList = new LinkedList<Class<?>>();

    private static DemoDatabaseHelper instance;

    public DemoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        instance = this;

        try {
            classList.add(Issue.class);

            for (Class<?> clazz : classList) {
                DaoManager.createDao(getConnectionSource(), clazz);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * This is called when the database is first created. Usually you should
     * call createTable statements here to create the tables that will store
     * your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        createTables();
    }

    /**
     * This is called when your application is upgraded and it has a higher
     * version number. This allows you to adjust the various data to match the
     * new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion,
                          int newVersion) {
        dropTables();
        createTables();
    }

    public Dao<?, ?> getDAO(Class<?> type)  {
        try {
            return DaoManager.createDao(connectionSource, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
    }

    private void createTables() {

        try {
            for (Class<?> clazz : classList) {
                TableUtils.createTable(getConnectionSource(), clazz);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void dropTables() {

        try {
            for (Class<?> clazz : classList) {
                TableUtils.dropTable(getConnectionSource(), clazz, true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DemoDatabaseHelper getInstance() {
        return instance;
    }
}