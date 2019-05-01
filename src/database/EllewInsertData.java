package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import library.RegisteredStudent;

public class EllewInsertData extends EllewDatabase{
     Statement st;
     PreparedStatement psmnt = null; 
     int s;
    public EllewInsertData(String accountHolder,String student_id,String mDob,String rfid_number,
                           String address,String gender,String mobile,File image){
        st=EllewDatabeConnect();
        Date date=new Date();
        s=0;
        try{
            FileInputStream fis= new FileInputStream(image);
            psmnt = conn.prepareStatement("insert into name(account_holder,student_id,"
                          + "dob,rfid_number,thau,gender,date,mobilenumber,image,account_status)"
                                       + "values(?,?,?,?,?,?,?,?,?,?)");
            psmnt.setString(1,accountHolder);
            psmnt.setString(2,student_id);
            psmnt.setString(3,mDob);
            psmnt.setString(4,rfid_number);
            psmnt.setString(5,address);
            psmnt.setString(6,gender);
            psmnt.setString(7,date.toString());
            psmnt.setString(8,mobile);
            psmnt.setBinaryStream(9, (InputStream)fis, (int)(image.length()));
            psmnt.setString(10,"Active");
            s = psmnt.executeUpdate(); 
            if(s>0){
                //to show the registered data
                //new RegisteredStudent();
            }
        }catch(Exception e){
          e.printStackTrace();
        }  
    }
    public boolean getAcknowledge(){
        return s>0;
    }
}
