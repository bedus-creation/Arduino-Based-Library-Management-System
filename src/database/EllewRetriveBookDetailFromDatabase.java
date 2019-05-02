
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

public class EllewRetriveBookDetailFromDatabase extends EllewDatabase{
         Statement st;
         PreparedStatement psmnt;
         FileOutputStream fout;
         ResultSet mResultSet;
         String mRetriveImagePath;
         String mBookName,mBookSection,mBookAuthor,mBookBorrower;
         byte[] b;
    public EllewRetriveBookDetailFromDatabase(){
        st=EllewDatabeConnect();
        psmnt=null;
        fout=null;
        mResultSet=null;
        
        mBookName=null;
        mBookSection=null;
        mBookAuthor=null;
        b=null; 
    }  
    public void searchUsingBookID(String mBookID){
        String netID=mBookID.substring(0,9);
        String hostID=mBookID.substring(9,12);
        Process(netID,hostID);
    }
    public void searchUsingNetID(String netID){
        try{
           psmnt = conn.prepareStatement("SELECT * FROM book WHERE netid=?");
           psmnt.setString(1,netID);
           mResultSet=psmnt.executeQuery();
           while(mResultSet.next()){
              mBookName=mResultSet.getString("bookname");
              mBookAuthor=mResultSet.getString("author");
              mBookSection=mResultSet.getString("section");
              mBookBorrower=mResultSet.getString("borrower");
              
              mRetriveImagePath="C:\\Users\\Public\\Pictures\\"+netID+".png";
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
    public String getBookName(){
        return mBookName;
    }
    public String getBookAuthor(){
        return mBookAuthor;
    }
    public String getBookSection(){
        return mBookSection;
    }
    public String getBookBorrower(){
        return mBookBorrower;
    }
    public String getBookCoverImagLocation(){
        return mRetriveImagePath;
    }
    public void Process(String netID,String hostID){
        try{
           psmnt = conn.prepareStatement("SELECT * FROM book_details a, book b "
                    + "WHERE a.netid = b.netid AND b.netid=?"
                    + "AND a.hostid =? LIMIT 0,30");
           psmnt.setString(1,netID);
           psmnt.setString(2,hostID);
           mResultSet=psmnt.executeQuery();
           Blob blog=null;
           while(mResultSet.next()){
              mBookName=mResultSet.getString("bookname");
              mBookAuthor=mResultSet.getString("author");
              mBookSection=mResultSet.getString("section");
              mBookBorrower=mResultSet.getString("borrower");
              
              mRetriveImagePath="C:\\Users\\Public\\Pictures\\"+netID+".png";
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
    
    public static void main(String []args){
        EllewRetriveBookDetailFromDatabase apps=new EllewRetriveBookDetailFromDatabase();
        apps.searchUsingBookID("000000000012");
        System.out.println(apps.getBookAuthor());
    }
}
