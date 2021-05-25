package classes;
import application.BetterDBConnection;

import java.sql.ResultSet;

public class DBManager {

   public static void update(String query){
       new BetterDBConnection().update(query);
   }

   public static ResultSet select(String query){
      return new BetterDBConnection().select(query);
   }

   public static void delete(String query){
       new BetterDBConnection().delete(query);
   }

}
