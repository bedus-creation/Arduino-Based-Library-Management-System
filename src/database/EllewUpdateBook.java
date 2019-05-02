package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * This class is related to the book update
 */
public class EllewUpdateBook extends EllewDatabase{
    Statement st;
    PreparedStatement psmnt = null; 
    Date mDate;
    public EllewUpdateBook(){
        st=EllewDatabeConnect();
    }
    
    /**
     *
     * @param netID
     * @param mBookName
     * @param mBookAuthor
     * @param mBookSection
     */
    public void BookDetailsUpdate(String netID,String mBookName,String mBookAuthor,String mBookSection){
        try{
           psmnt = conn.prepareStatement("UPDATE `book` SET `bookname`=?,`author`=?  WHERE netid=?");
           psmnt.setString(1,mBookName);
           psmnt.setString(2,mBookAuthor);
           psmnt.setString(3,netID);
           int s = psmnt.executeUpdate();
           if(s>0){
                //to show the registered book
               System.out.println("Successfully updated the bookbrowser.");
            }
        }catch(SQLException e){
            
        }
    }
    public static void main(String []args){
        EllewUpdateBook ms=new EllewUpdateBook();
        ms.BookDetailsUpdate("123456555","Antenna and EM Modeling with Matlab","Sergey N. Makarov ","iii/ii");	
    }
}
