package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EllewUpdateState extends EllewDatabase{
    Statement st;
    PreparedStatement psmnt = null;
    public EllewUpdateState(int mTransactionID){
        st=EllewDatabeConnect();
        try{
           psmnt = conn.prepareStatement("UPDATE `transaction` SET `status`=? WHERE id=?");
           psmnt.setInt(1,1);
           psmnt.setInt(2,mTransactionID);
           int s = psmnt.executeUpdate();
           if(s>0){
                //to show the registered book
                System.out.println("Successfully updated the transaction.");
            }
        }catch(SQLException e){
            
        }
    }
}
