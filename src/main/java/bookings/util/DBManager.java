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

    private static void dbDisconnect() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public static ResultSet dbExecuteQuery(
            String query, Object... varArgs
    ) throws SQLException, ClassNotFoundException {
        dbConnect();
        PreparedStatement ps = conn.prepareStatement(query);

        // Sets the value of every designated parameter
        for (int i = 0; i < varArgs.length; i++) {
            ps.setObject(i + 1, varArgs[i]);
        }

        ResultSet rs = ps.executeQuery();

        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
        crs.populate(rs);

        if (rs != null) {
            rs.close();
        }

        ps.close();
        dbDisconnect();
        return crs;
    }



    public static void dbExecuteUpdate(
            String query, Object... varArgs
    ) throws SQLException, ClassNotFoundException {
        dbConnect();
        PreparedStatement ps = conn.prepareStatement(query);

        // Sets the value of every designated parameter
        for (int i = 0; i < varArgs.length; i++) {
            ps.setObject(i + 1, varArgs[i]);
        }

        ps.executeUpdate();

        ps.close();
        dbDisconnect();
    }

}