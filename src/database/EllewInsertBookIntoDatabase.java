package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import library.RegisteredBook;

public class EllewInsertBookIntoDatabase extends EllewDatabase{
    Statement st;
    PreparedStatement psmnt = null; 
    Date mDate;
    public EllewInsertBookIntoDatabase(String mBookName,String netID,String mAuthorName,
                                        String section,int mNumberOfBook,File mCoverImage ){
        st=EllewDatabeConnect();
        mDate=new Date();
        try{
            FileInputStream fis= new FileInputStream(mCoverImage);
            psmnt = conn.prepareStatement("insert into book(bookname,netid,"
                                          +"author,registered,section,number,image)"
                                       + "values(?,?,?,?,?,?,?)");
            psmnt.setString(1,mBookName);
            
            psmnt.setString(2,netID);
            psmnt.setString(3,mAuthorName);
            psmnt.setString(4,mDate.toString());
            psmnt.setString(5,section);
            psmnt.setInt(6,mNumberOfBook);
            psmnt.setBinaryStream(7, (InputStream)fis, (int)(mCoverImage.length()));
            int s = psmnt.executeUpdate(); 
            if(s>0){
                //to show the registered book
                new EllewInsertBookDetailsIntoDatabase(netID,mNumberOfBook);
                new RegisteredBook(mCoverImage,mBookName,netID,mAuthorName,section,mDate.toString());
            }
        }catch(Exception e){
            e.printStackTrace();
        }    
    }
}
