import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {
    private Connection dbConn;
    private Statement stat;

    public Connection getConnection() {
        String DB_id = "sql4412131";
        String DB_pass = "azsNhGPJfq";
        String DB_url = "jdbc:mysql://sql4.freemysqlhosting.net/sql4412131";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownikow JDBC");
            e.printStackTrace();
        }
        try {
            dbConn = DriverManager.getConnection(DB_url, DB_id, DB_pass);
            stat = dbConn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }

        return dbConn;
    }


}