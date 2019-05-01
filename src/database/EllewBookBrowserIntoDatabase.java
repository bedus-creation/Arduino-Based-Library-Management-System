package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * This class is related to the book update
 */
public class EllewBookBrowserIntoDatabase extends EllewDatabase{
    Statement st;
    PreparedStatement psmnt = null; 
    Date mDate;
    public EllewBookBrowserIntoDatabase(){
        st=EllewDatabeConnect();
    }
    public void InsertBookBorrowerIntoDatabase(String netID,String hostID,String mStudentID){
        try{
           psmnt = conn.prepareStatement("UPDATE `book_details` SET `borrower`=? WHERE netid=? AND hostid=?");
           
           psmnt.setString(1,mStudentID);
           psmnt.setString(2,netID);
           psmnt.setString(3,hostID);
           int s = psmnt.executeUpdate();
           if(s>0){
                //to show the registered book
               System.out.println("Successfully updated the bookbrowser.");
            }
        }catch(SQLException e){
            
        }
    }
    public void RemoveBookBorrowerIntoDatabase(String netID,String hostID,String mStudentID){
        try{
           psmnt = conn.prepareStatement("UPDATE `book_details` SET `borrower`=? WHERE netid=? AND hostid=?");
           
           psmnt.setString(1,"Not Issued");
           psmnt.setString(2,netID);
           psmnt.setString(3,hostID);
           int s = psmnt.executeUpdate();
           if(s>0){
                //to show the registered book
               System.out.println("Successfully updated the bookbrowser.");
            }
        }catch(SQLException e){
            
        }
    }
}
