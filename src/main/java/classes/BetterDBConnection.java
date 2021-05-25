package classes;
import java.sql.*;

public class BetterDBConnection {


    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://sql4.freemysqlhosting.net/sql4412131";
    static final String DB_USER = "sql4412131";
    static final String DB_PASSWORD = "azsNhGPJfq";
    Connection connection;

    public BetterDBConnection(){
        connect();
    }

    public void connect(){
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }catch(SQLException|ClassNotFoundException e){
            System.out.println("ERROR connecting to database!");
            System.out.println(e.toString());
        }
    }

    public ResultSet select(String query){
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            return result;
        }catch(SQLException e){
            System.out.println("ERROR while executing select query!");
            System.out.println(e.toString());
            return null;
        }
    }

    public int update(String query){
        try{
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        }catch(SQLException e){
            System.out.println("ERROR while executing update query");
            System.out.println(e.toString());
            return -1;
        }
    }

    public int delete(String query){
        try{
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        }catch(SQLException e){
            System.out.println("ERROR while deleting line!");
            System.out.println(e.toString());
            return -1;
        }
    }

    public void close(){
        try{
            connection.close();
        }catch(SQLException e){
            System.out.println("ERROR while closing connections!");
            System.out.println(e.toString());
        }
    }

}
