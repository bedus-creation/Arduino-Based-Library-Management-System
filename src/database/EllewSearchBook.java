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

public class EllewSearchBook extends EllewDatabase{
         Statement st;
         PreparedStatement psmnt;
         FileOutputStream fout;
         ResultSet mResultSet;
         String mRetriveImagePath;
         ArrayList<String>mBookID;
         String mBookAuthor,mBookLocation;
         byte[] b;
    public EllewSearchBook(){
        st=EllewDatabeConnect();
        psmnt=null;
        fout=null;
        mResultSet=null;
        mRetriveImagePath=null;
        mBookID=new ArrayList<String>();
        mBookAuthor=null;
        mBookLocation=null;
        b=null;
        Process();
    }
    public ArrayList getList(String searchBookID){
        ArrayList<String> hint=new ArrayList<String>();
        searchBookID = searchBookID.toLowerCase();
        int len=searchBookID.length();
        for (String mBookID1 : mBookID) {
            if (mBookID1.toLowerCase().startsWith(searchBookID)) {
                if(hint.toString().equals("")){
                    showSlectedUser(searchBookID);
                }else{
                    hint.add(mBookID1);
                }
            }
        }
        return hint;
    }
    private void Process(){
        try{
           psmnt = conn.prepareStatement("SELECT * FROM book_details a, book b "
                    + "WHERE a.netid = b.netid");
           mResultSet=psmnt.executeQuery();
           while(mResultSet.next()){
              String mBookName=mResultSet.getString("bookname"); 
              mBookID.add(mResultSet.getString("netid")+mResultSet.getString("hostid"));
              mRetriveImagePath="C:\\Users\\Public\\Pictures\\"+mBookName+".png";
              if(new File(mRetriveImagePath).exists()){
                  //  System.out.println(mRetriveImagePath+" Already Exists");
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
    public void showSlectedUser(String mBookID) {
        System.out.println(mBookID);
    }
}
