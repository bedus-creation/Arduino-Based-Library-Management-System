/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Install;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author hackers
 */
public class InitializeDatabase extends ConnectDatabase{
    Statement st;
    PreparedStatement psmt;
    public InitializeDatabase(){
        st=super.getStatement();
        Date date=new Date();
        try{
            psmt=conn.prepareStatement("INSERT INTO library_admin"
                    + "(date,username,password,)");
            /*
            * add some code to complete
            */
        }catch(SQLException e){
            
        }
    }
}
