/*
 * This Code perfectly fetches the bookid as well as corressonding student ID.
 */
package testing;

import database.EllewDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTesting2 extends EllewDatabase{
    Statement st;
    ResultSet mResultSet,mName;
    String mBookID;
    PreparedStatement psmnt=null;
    public DatabaseTesting2(){
        try{ 
        st=EllewDatabeConnect();
        psmnt=conn.prepareStatement("SELECT * FROM transaction WHERE status=?");
        psmnt.setInt(1,0);
        mResultSet=psmnt.executeQuery();
        while(mResultSet.next()){
                mBookID=mResultSet.getObject(2).toString();
                String mStudentID=mResultSet.getObject(3).toString();
                System.out.println("The Book id is :"+mBookID+
                                   "\nThe Student ID is :"+mStudentID+"\n");
                int listno=mResultSet.getInt("id");
                System.out.println(listno);
         }
        
        }catch(SQLException ex){
            
        }
                
    }
    public boolean CheckBook(String mStudentID){
        boolean tmp=false;
        try{
            psmnt=conn.prepareStatement("SELECT * FROM name WHERE `rfid_number`=?");
            psmnt.setString(1,mStudentID);
            mName=psmnt.executeQuery();
            if(mName.next()){
                tmp=true;
                System.out.println(mName);
            }
        }catch(SQLException e){
            
        }                    
        return tmp;
    }
    public static void main(String []args){
        DatabaseTesting2 app=new DatabaseTesting2();
        if(app.CheckBook("12344")){
            System.out.println("Registered.");
        }else{
            System.out.println("Not Registered.");
        }
    }
}
