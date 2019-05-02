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

public class EllewRetriveStudentDetailFromDatabase extends EllewDatabase{
         Statement st;
         PreparedStatement psmnt;
         FileOutputStream fout;
         ResultSet mResultSet;
         String mRetriveImagePath;
         String mStudentName,mStudentBatch,mStudentAddress,mStudentRFID;
         ArrayList<String> mBookList;
         byte[] b;
    public EllewRetriveStudentDetailFromDatabase(){
        st=EllewDatabeConnect();
        psmnt=null;
        fout=null;
        mResultSet=null;
        mRetriveImagePath=null;
        mStudentName=null;
        mStudentBatch=null;
        mStudentAddress=null;
        mStudentRFID=null;
        b=null;
        mBookList=new ArrayList<>();
    }
    public String getStudentName(){
        return mStudentName;
    }
    public String getStudentID(){
        return mStudentBatch;
    }
    public String getStudentAddress(){
        return mStudentAddress;
    }
    public String getStudentImageLocation(){
        return mRetriveImagePath;
    }
    public String getStudentRFID(){
        return mStudentRFID;
    }
    public void searchUsingRFID(String mRFID_number){
        System.out.print(mRFID_number);
        try{
           psmnt = conn.prepareStatement("SELECT * FROM `name` WHERE rfid_number=?");
           psmnt.setString(1,mRFID_number);
           mResultSet=psmnt.executeQuery();
           while(mResultSet.next()){
              mStudentName=mResultSet.getString("account_holder");
              mStudentBatch=mResultSet.getString("student_id");
              mStudentAddress=mResultSet.getString("thau");
              mStudentRFID=mResultSet.getString("rfid_number");
              mRetriveImagePath="C:\\Users\\Public\\Pictures\\"+mStudentBatch+".png";
              if(new File(mRetriveImagePath).exists()){
                    System.out.println("Already Exists");
              }else{
                  b=mResultSet.getBytes("image");
                  fout=new FileOutputStream(mRetriveImagePath);    
                  fout.write(b);
              }
           }    
        }catch(SQLException e){
            
        }catch (FileNotFoundException ex) {
            
        }catch (IOException ex) {
            
        }
    }
    public void searchUsingStudentID(String mStudentID){
        try{
           psmnt = conn.prepareStatement("SELECT * FROM name "
                   + " WHERE student_id=?");
           psmnt.setString(1,mStudentID);
           mResultSet=psmnt.executeQuery();
           while(mResultSet.next()){
              mStudentName=mResultSet.getString("account_holder");
              mStudentBatch=mResultSet.getString("student_id");
              mStudentAddress=mResultSet.getString("thau");
              mStudentRFID=mResultSet.getString("rfid_number");
              mRetriveImagePath="C:\\Users\\Public\\Pictures\\"+mStudentBatch+".png";
              if(new File(mRetriveImagePath).exists()){
                    System.out.println("Already Exists");
              }else{
                  b=mResultSet.getBytes("image");
                  fout=new FileOutputStream(mRetriveImagePath);    
                  fout.write(b);
              }
           }    
        }catch(SQLException e){
            
        }catch (FileNotFoundException ex) {
            
        }catch (IOException ex) {
            
        }
    }
    public ArrayList<String> getIssuedBookList(String mStudentID){
        try{
           psmnt = conn.prepareStatement("SELECT * FROM book_details a,name b"
                   + " WHERE a.borrower=b.student_id AND b.student_id=?");
           psmnt.setString(1,mStudentID);
           mResultSet=psmnt.executeQuery();
           while(mResultSet.next()){
               mBookList.add(mResultSet.getString("netid")+mResultSet.getString("hostid"));
           }
        }catch(SQLException e){
            
        }
        return mBookList;
    }
    public static void main(String args[]){
         EllewRetriveStudentDetailFromDatabase mS=new EllewRetriveStudentDetailFromDatabase();
         mS.searchUsingRFID("35665555765485753484867542");
         System.out.print(mS.mRetriveImagePath);
    }
}
