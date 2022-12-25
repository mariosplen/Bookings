package bookings.models;


import bookings.util.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAO {

    public static ObservableList<Category> getCategories() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM room_categories";
        ResultSet rs = DBManager.dbExecuteQuery(query);

        ObservableList<Category> categories = getCategoriesFromRs(rs);
        rs.close();
        return categories;
    }


    public static Category getCategoryFromRs(ResultSet rs) throws SQLException {
        return new Category(rs.getString("category"));
    }

    public static ObservableList<Category> getCategoriesFromRs(ResultSet rs) throws SQLException {
        ObservableList<Category> categories = FXCollections.observableArrayList();
        while (rs.next()) {
            categories.add(getCategoryFromRs(rs));
        }
        return categories;
    }


}
