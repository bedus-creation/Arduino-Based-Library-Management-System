package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenerateNetIDForNewBook extends EllewDatabase{
    Statement st;
    PreparedStatement psmnt=null;
    ResultSet mResultSet=null;
    int netID=000;
    public GenerateNetIDForNewBook(){
        st=EllewDatabeConnect();
        try{
        psmnt = conn.prepareStatement("SELECT netid FROM book");
        mResultSet=psmnt.executeQuery();
        mResultSet.last(); 
        if(mResultSet.getString("netid")!=null){
            String mVale=mResultSet.getString("netid");
            netID=Integer.parseInt(mVale);
            netID=netID+001;    
        }else{
            netID=001;
        }
    }catch(SQLException e){
            
        } 
    }
    public String getNetID(){
        return String.format("%09d",netID);
    }
    public static void main(String[]args){
        GenerateNetIDForNewBook mGenerateNetIDForNewBook=new GenerateNetIDForNewBook();
    }
}