package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class EllewDatabase {
         public Connection conn=null;
         String url = "jdbc:mysql://localhost:3306/";
         String dbName= "java_details";
         String driver = "com.mysql.jdbc.Driver";
         String userName = "root"; 
         String password = "#bedu123TamanG";
         Statement st;
    public Statement EllewDatabeConnect(){  
         PreparedStatement ps=null;
         try  {
              Class.forName(driver).newInstance();
              this.conn= DriverManager.getConnection(url+dbName,userName,password);
              st = conn.createStatement();
              }catch(Exception e){
                    e.printStackTrace();
              }
         return st;
     }
    public Connection getConnection(){
        return conn;
    }
}
