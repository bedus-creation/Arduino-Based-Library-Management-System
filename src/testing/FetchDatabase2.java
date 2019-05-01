package testing;

import database.EllewDatabase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FetchDatabase2 extends EllewDatabase implements Runnable{
    Statement st;
    ResultSet mResultSet,mName,mBook;
    @Override
    public void run() {
        while(true){
            try{
                st=EllewDatabeConnect();
                mResultSet=st.executeQuery("SELECT a.id,a.status ,a.fullname,a.bookname,b.rfid_number,c.bookid"
                        + " FROM transaction a,name b,book c "
                        + " WHERE a.fullname=b.rfid_number && a.bookname=c.bookid && a.status=0");
                mName=st.executeQuery("SELECT * FROM transaction WHERE status==0");
            }catch(SQLException e){
                
            }
            try{
                Thread.sleep(3000);
            }catch(InterruptedException e){
                
            } 
        }//end of while function
    } //end of the run function
}
