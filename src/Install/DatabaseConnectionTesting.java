package Install;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class DatabaseConnectionTesting {
    public Connection conn=null;
    String url = "jdbc:mysql://localhost:3306/";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root"; 
    String password = "";
    Statement st;
    public DatabaseConnectionTesting(){
        try{
            Class.forName(driver).newInstance();
            this.conn= DriverManager.getConnection(url,userName,password);
            st = conn.createStatement();
            System.out.println("Successfully connected to the database.");
        }catch(Exception e){
                    System.out.println("Unable to connect to the database");
                    System.out.println(e.getMessage());
                    System.exit(1);
              }
    } 
    protected Statement getStatement(){
        return st;
    }
}