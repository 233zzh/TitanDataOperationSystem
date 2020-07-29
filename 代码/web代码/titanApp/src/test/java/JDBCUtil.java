import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {

    private final static String url = "jdbc:mysql://localhost:3306/TiTan?serverTimezone=UTC&characterEncoding=utf-8";

    private final static String driver = "com.mysql.cj.jdbc.Driver";
    private final static String username = "root";
    private final static String password = "12345678";
    private static Connection connection;

    public JDBCUtil() {
    }
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeConnection(Connection connection, Statement statement) {
        try {
            if(connection != null) {
                connection.close();;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
