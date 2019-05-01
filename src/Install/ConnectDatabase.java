package Install;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectDatabase {
    public Connection conn=null;
    String database="java_details";
    String url = "jdbc:mysql://localhost:3306/";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root"; 
    String password = "#bedu123TamanG";
    Statement st;
    public ConnectDatabase(){
         try{
            Class.forName(driver).newInstance();
            this.conn= DriverManager.getConnection(url+database,userName,password);
            st = conn.createStatement();
            System.out.println("Successfully connected to the database.");
        }catch(Exception e){
                    System.out.println("Unable to connect to the database");
                    System.out.println(e.getMessage());
                    System.exit(1);
              }
    } 
    public Statement getStatement(){
        return st;
    }
}
