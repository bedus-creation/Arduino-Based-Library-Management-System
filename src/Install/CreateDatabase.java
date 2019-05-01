package Install;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase extends DatabaseConnectionTesting{
    Statement st;
    PreparedStatement psmnt = null; 
    public CreateDatabase(){
        st=super.getStatement();
        try{
            psmnt = conn.prepareStatement("CREATE DATABASE java_details");
            int s = psmnt.executeUpdate(); 
            if(s>0){
                System.out.println("successfully created the database.");
            }
        }catch(SQLException e){
            
        }
    }
}
