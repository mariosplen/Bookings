package bookings.util;

import java.sql.SQLException;

public interface PageController {

    void setUsername(String userName);

    void lateInitialize() throws SQLException, ClassNotFoundException;

}
