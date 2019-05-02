package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * This class is related to the book update
 */
public class EllewUpdateStudent extends EllewDatabase{
    Statement st;
    PreparedStatement psmnt = null; 
    Date mDate;
    public EllewUpdateStudent(){
        st=EllewDatabeConnect();
    }
    
    /**
     *
     * @param mStudentID :  Student ID
     * @param mStudentName
     * @param mStudentAddress
     */
    public boolean StudentDetailsUpdate(String mStudentID,String mStudentName,String mStudentAddress){
        boolean temp=false;
        try{
           psmnt = conn.prepareStatement("UPDATE `name` SET `account_holder`=?,`thau`=?  WHERE student_id=?");
           psmnt.setString(1,mStudentName);
           psmnt.setString(2,mStudentAddress);
           psmnt.setString(3,mStudentID);
           int s = psmnt.executeUpdate();
           if(s>0){
               //to show the registered book
               temp=true;
               System.out.println("Successfully updated the bookbrowser.");
            }
        }catch(SQLException e){
            
        }
        return temp;
    }
    public static void main(String []args){
        EllewUpdateStudent ms=new EllewUpdateStudent();
        ms.StudentDetailsUpdate("Allen070Bex06","Allen Khawas","Pathari");	
    }
}
