package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conn {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/anuaire";
    private static final String USERNAME = "ch";
    private static final String PASSWORD = "***";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to establish a database connection.", e);
        }
    }
}
