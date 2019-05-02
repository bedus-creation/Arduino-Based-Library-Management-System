package testing;

import database.EllewDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author hackers
 */
public class DatabaseUpateTesting extends EllewDatabase{
    Statement st;
    PreparedStatement psmnt = null; 
    Date mDate;
    public DatabaseUpateTesting(String mBookID,String mStudentID){
        st=EllewDatabeConnect();
        try{
           ResultSet resultSet =st.executeQuery( "UPDATE `book` SET `borrower`='"
                                      +mStudentID+"' WHERE bookid='"+mBookID+"'" );
           if(resultSet==null){
               System.out.println("Sorry");
           }
        }catch(SQLException e){
            
        }
    }
    public static void main(String[]args){
        DatabaseUpateTesting app=new DatabaseUpateTesting("123456788","12344");
    }
}
