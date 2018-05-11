/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capytecmaster;

import java.sql.*;

/**
 *
 * @author steve
 */
class connection {
    
private static final String database = "db/Capytec.accdb";
private static final String driver = "jdbc:ucanaccess://";
private static final String url = driver + database;
protected static Connection connection = null;
    
    public static Connection connect () {
         try {
          connection = DriverManager.getConnection (url);
                    // unless you’ve set up a username or password…
          System.err.println ("Connection successful\n");
        } catch (SQLException sqlex) {
          System.err.println ("Connection unsuccessful\n" + sqlex.toString ());
        } catch (Exception ex) {//remaining exceptions
          System.err.println (ex.toString ());
        }
      return connection;
    }
} // end of connection class
