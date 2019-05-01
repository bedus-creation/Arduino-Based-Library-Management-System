package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EllewSearchStudent extends EllewDatabase{
         Statement st;
         PreparedStatement psmnt;
         FileOutputStream fout;
         ResultSet mResultSet;
         String mRetriveImagePath;
         ArrayList<String> mStudentName,mStudentBatch;
         String mStudentAddress,mStudentRFID;
         byte[] b;
    public EllewSearchStudent(){
        st=EllewDatabeConnect();
        psmnt=null;
        fout=null;
        mResultSet=null;
        mRetriveImagePath=null;
        mStudentName=new ArrayList<String>();
        mStudentBatch=new ArrayList<String>();
        mStudentAddress=null;
        mStudentRFID=null;
        b=null;
        Process();
    }
    public ArrayList getList(String mStudentID){
        ArrayList<String> hint=new ArrayList<String>();
        mStudentID = mStudentID.toLowerCase();
        int len=mStudentID.length();
        for (String mStudentBatch1 : mStudentBatch) {
            if (mStudentBatch1.toLowerCase().startsWith(mStudentID)) {
                if(hint.toString().equals("")){
                    showSlectedUser(mStudentID);
                }else{
                    hint.add(mStudentBatch1);
                }
            }
        }
        return hint;
    }
    private void Process(){
        try{
           psmnt = conn.prepareStatement("SELECT * FROM `name` ORDER BY student_id");
           mResultSet=psmnt.executeQuery();
           Blob blog=null;
           int i=0;
           while(mResultSet.next()){
              mStudentName.add(mResultSet.getString("account_holder"));
              mStudentBatch.add(mResultSet.getString("student_id"));
              mStudentAddress=mResultSet.getString("thau");
              mStudentRFID=mResultSet.getString("rfid_number");
              //System.out.println(mStudentBatch);
              mRetriveImagePath="C:\\Users\\Public\\Pictures\\"+mStudentName+".png";
              if(new File(mRetriveImagePath).exists()){
                  //  System.out.println(mRetriveImagePath+" Already Exists");
              }else{
                  b=mResultSet.getBytes("image");
                  fout=new FileOutputStream(mRetriveImagePath);    
                  fout.write(b);
              }
              i++;
           }    
        }catch(SQLException e){
            
        }catch (FileNotFoundException ex) {
            
        }catch (IOException ex) {
            
        }
    }
    public void showSlectedUser(String mStudentID) {
        System.out.println(mStudentID);
    }
}
