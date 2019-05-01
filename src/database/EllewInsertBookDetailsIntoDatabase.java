package database;
/*
* This class is applicable to the new Book Registration 
*/
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EllewInsertBookDetailsIntoDatabase extends EllewDatabase {
    Statement st;
    PreparedStatement psmnt = null; 
    public EllewInsertBookDetailsIntoDatabase(String netID,int number){
        st=EllewDatabeConnect();
        for(int i=1;i<=number;i++){
            String hostID=String.format("%03d",i);
            Insert(netID,hostID);
        }    
    }
    private void Insert(String netID,String hostID){
       try{
            psmnt = conn.prepareStatement("insert into book_details(netid,hostid)"+
                                       "values(?,?)");
            psmnt.setString(1,netID);
            psmnt.setString(2,hostID);
            int s = psmnt.executeUpdate(); 
        }catch(SQLException e){
            
        } 
    }
    
    /*
    public static void main(String []args){
        EllewInsertBookDetailsIntoDatabase apps=new EllewInsertBookDetailsIntoDatabase("000000001",50);
    }
    */
}
