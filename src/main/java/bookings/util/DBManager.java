package bookings.util;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DBManager {
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String connStr = "jdbc:sqlite:assets/database/bookings.db";
    private static Connection conn = null;

    private static void dbConnect() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(connStr);
    }

    public static void dbDisconnect() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public static ResultSet dbExecuteQuery(String queryStmt, Object... varArgs) throws SQLException, ClassNotFoundException {
        dbConnect();
        PreparedStatement stmt = conn.prepareStatement(queryStmt);

        // Sets the value of every designated parameter
        for (int i = 0; i < varArgs.length; i++) {
            stmt.setObject(i + 1, varArgs[i]);
        }

        ResultSet resultSet = stmt.executeQuery();

        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
        crs.populate(resultSet);

        if (resultSet != null) {
            resultSet.close();
        }

        stmt.close();
        dbDisconnect();
        return crs;
    }

}

