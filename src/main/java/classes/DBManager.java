package classes;
import application.BetterDBConnection;

import java.sql.ResultSet;

public class DBManager {


   static void update(String query){
       new BetterDBConnection().update(query);
   }

   static ResultSet select(String query){
      return new BetterDBConnection().select(query);
   }

   static void delete(String query){
       new BetterDBConnection().delete(query);
   }

}
