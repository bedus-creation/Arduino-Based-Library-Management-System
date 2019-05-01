package database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EllewLogin extends EllewDatabase{
    boolean loginStatus=false;
    Statement st;
        PreparedStatement psmnt;
        ResultSet mResultSet;
    public EllewLogin(String username,String password){
        Statement st=EllewDatabeConnect();
        st=EllewDatabeConnect();
        psmnt=null;
        try{
           psmnt = conn.prepareStatement("SELECT * FROM `library_admin` WHERE username=?");
           psmnt.setString(1,username);
           mResultSet=psmnt.executeQuery();
           if(mResultSet.next() && password.equals(mResultSet.getString("password"))){
                this.loginStatus=true;
            }else{
                System.out.println("Login Error .");
            }   
        }catch(Exception e){
          e.printStackTrace();
      }
    }
    public boolean getLoginStatus(){
        return loginStatus;
    }
}
