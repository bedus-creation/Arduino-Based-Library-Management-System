package library;

import database.EllewDatabase;
import database.EllewRetriveBookDetailFromDatabase;
import database.EllewRetriveStudentDetailFromDatabase;
import database.EllewUpdateState;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import testing.AudioPlay;

public class UpdateDatabase extends EllewDatabase implements Runnable{
    Main mMain;
    Statement st;
    ResultSet mResultSet,mName,mBook,mTemp;
    ShowBook mShowBook;
    PreparedStatement psmnt = null; 
    public UpdateDatabase(Main main){
        this.mMain=main;
    }
    @Override
    public void run() {
        while(true){
            try {
                st=EllewDatabeConnect();
                mTemp=st.executeQuery("SELECT * FROM transaction WHERE status=0");
                while(mTemp.next()){
                        System.out.println("Now the database is started .");
                        String mStudentID=mTemp.getString("student_id");
                        String netID     =mTemp.getString("net_id");
                        String hostID   =mTemp.getString("host_id");
                        System.out.println("Now the database is started .");
                        int issue=mTemp.getInt("issue");
                        int mTransactionID=mTemp.getInt("id");
                            if(CheckBook(netID,hostID,issue,mTransactionID)
                                    && CheckStudent(mStudentID,netID,issue,mTransactionID)){
                                
                                showConformationFrame(netID,hostID,mStudentID,mTransactionID,issue);
                            }/*else if(!CheckBook(netID,hostID,issue,mTransactionID)){
                                //forcetoBookRegistration(netID,hostID);
                            } else if(!CheckStudent(mStudentID,netID,mTransactionID)){
                                //forceToStudentRegistration(mStudentID) ; 
                            }*/else{
                                   System.out.println("There is something else.");
                                   
                                   //JOptionPane.showMessageDialog(mMain,"Either book or Student ID is not valid.","error",JOptionPane.ERROR_MESSAGE,null);
                                   
                            }
                        }//end of while loop
                if(mTemp.next()){
                }else{        
                    System.out.print("Empty transaction in database.");                   
                } 
                                      
            }catch (SQLException ex) {
                            //Write the catch block    
            }
            try{
                 Thread.sleep(3000);
            }catch(InterruptedException e){
                 
            }    
        }//end of while
    }
    public void showConformationFrame(String netID,String hostID,String mStudentID,int mTransactionID,int issue){    
            switch(issue){   
                case 0:
                    ShowReturn mShowReturn=new ShowReturn(netID,hostID,mStudentID,mTransactionID);
                    while(mShowReturn.isVisible()){
                        try{
                            Thread.sleep(100);
                        }catch(InterruptedException e){
                                    
                        }
                    }//end of while loop  
                    break;
                case 1:
                    ShowIssue mShowIssue=new ShowIssue(netID,hostID,mStudentID,mTransactionID);
                    while(mShowIssue.isVisible()){
                        try{
                            Thread.sleep(100);
                        }catch(InterruptedException e){
                                    
                        }
                    }//end of while loop  
                    break;    
                default:
                    new EllewUpdateState(mTransactionID);
                    JOptionPane.showMessageDialog(mMain,"The Transaction is not valid.","error",JOptionPane.ERROR_MESSAGE,null);
                    break;
            }                      
    }
    private boolean CheckStudent(String mStudentRFID,String netID,int issue,int mTransactionID){
        boolean tmp2=maxBookLimitError(mStudentRFID);
        if(!checkStudentIDValidity(mStudentRFID)){
            new EllewUpdateState(mTransactionID);
            JOptionPane.showMessageDialog(mMain,
                            "This student has not registered or"+
                            "\nThe account might has been blocked",
                            "Unregistered student",JOptionPane.INFORMATION_MESSAGE,null);
            return false;
        }else if(issue==1 && CheckRepeatStudent(mStudentRFID,netID)){
            EllewRetriveBookDetailFromDatabase mRBDFD=new EllewRetriveBookDetailFromDatabase();
            mRBDFD.searchUsingNetID(netID);  
            ImageIcon bookIcon=new ImageIcon(mRBDFD.getBookCoverImagLocation());
             AudioPlay mPlay=new AudioPlay("error");
            JOptionPane.showMessageDialog(mMain,
                            "You have already issused this book\n"+
                            "\nName      :   "+mRBDFD.getBookName() +
                            "\nBook ID   :   "+netID+      
                            "\nAuthor    :   "+ mRBDFD.getBookAuthor(),
                            mRBDFD.getBookName(),JOptionPane.INFORMATION_MESSAGE,bookIcon);
            new EllewUpdateState(mTransactionID);
            return false;
        }else if(!tmp2){
            EllewRetriveStudentDetailFromDatabase mERSDFD=new EllewRetriveStudentDetailFromDatabase();
            mERSDFD.searchUsingStudentID(mStudentRFID);
            AudioPlay mPlay=new AudioPlay("error");
            ImageIcon studentIcon=new ImageIcon(mERSDFD.getStudentImageLocation());
            JOptionPane.showMessageDialog(mMain,"The have already issued 9 books\n"
                    + "to your name.","error",JOptionPane.INFORMATION_MESSAGE,studentIcon);
            new EllewUpdateState(mTransactionID);
            return false;
        }else{         
            return true; 
        }
    }
    private boolean checkStudentIDValidity(String mStudentRFID){
        boolean tmp=false;    
        try{
        psmnt=conn.prepareStatement("SELECT * FROM `name` WHERE rfid_number=?");
            psmnt.setString(1,mStudentRFID);
            mName= psmnt.executeQuery();
            if(mName.next()){
                tmp= true;
            }else{
                tmp= false;
            }
        }catch(SQLException e){
            
        }
        return tmp;
    }
    public boolean CheckRepeatStudent(String mStudentRFID,String netID){
        /* returns true if error */
        boolean tmp=true;
        try{
            st=EllewDatabeConnect();
            psmnt=conn.prepareStatement("SELECT * FROM book_details a,name b"
                   + " WHERE a.borrower=b.student_id AND a.netid=? AND b.rfid_number=? ");
            psmnt.setString(1,netID);
            psmnt.setString(2,mStudentRFID);
            mName=psmnt.executeQuery();
            
            if(mName.next()){
                System.out.println("This is rediculous. ");
                
            }else{
                tmp=false;
            }
        }catch(SQLException e){
            
        }                    
        return tmp;
    }
    /*
    * This function returns false for positive 
    * This function works fine 
    */
   
    public boolean CheckBook(String netID,String hostID,int issue,int mTransactionID){
        if(!(netID.length()==9 && hostID.length()==3)){
            new EllewUpdateState(mTransactionID);
            JOptionPane.showMessageDialog(mMain,"The data is corrupted.\n"
                    + "please rescan and send.","error",JOptionPane.ERROR_MESSAGE,null);
            
            return false;
        }else return checkBookError(netID,hostID,issue,mTransactionID);
    }
    private boolean checkBookError(String netID,String hostID,int issue,int mTransactionID){
        boolean tmp=false;
        try{
            psmnt=conn.prepareStatement("SELECT * FROM book_details WHERE `netid`=? and `hostid`=?");
            psmnt.setString(1,netID);
            psmnt.setString(2,hostID);
            mName=psmnt.executeQuery();
            if(mName.next()){
                boolean notIssued=mName.getString("borrower").equals("Not Issued");
                boolean issued=!notIssued;
                /* trying to issue */
                 if(issue==1 && notIssued){
                    tmp=true; 
                 }
                 /* trying to issue but already issued*/
                 else if(issue==1 && issued){
                     /*the book is resistered to the name */
                     EllewRetriveBookDetailFromDatabase mRBDFD=new EllewRetriveBookDetailFromDatabase();
                     mRBDFD.Process(netID, hostID);
                     ImageIcon bookIcon=new ImageIcon(mRBDFD.getBookCoverImagLocation());
                     AudioPlay mPlay=new AudioPlay("error");
                      new EllewUpdateState(mTransactionID);
                     JOptionPane.showMessageDialog(mMain,"The Book is already issued."
                             +"\nBook Name   :"+mRBDFD.getBookName()
                             +"\nBook ID     :"+netID+hostID,"error",JOptionPane.ERROR_MESSAGE,bookIcon);
                    
                    tmp=false;
                 }
                 /*trying to return but not issued*/
                 else if(issue==0 && notIssued){
                     /* Show error */
                     EllewRetriveBookDetailFromDatabase mRBDFD=new EllewRetriveBookDetailFromDatabase();
                     mRBDFD.Process(netID, hostID);
                     ImageIcon bookIcon=new ImageIcon(mRBDFD.getBookCoverImagLocation());
                     AudioPlay mPlay=new AudioPlay("error");
                     new EllewUpdateState(mTransactionID);
                     JOptionPane.showMessageDialog(mMain,"The Book is Not issued yet."
                             +"\nBook Name   :"+mRBDFD.getBookName()
                             +"\nBook ID     :"+netID+hostID,"error",JOptionPane.ERROR_MESSAGE,bookIcon);
                     tmp=false;
                }else{
                      tmp=true;
                 }
            }else{
                new EllewUpdateState(mTransactionID);
                JOptionPane.showMessageDialog(mMain,"The Book that you want"
                        + " doesnot registered into the database.","Book is not registered.",JOptionPane.ERROR_MESSAGE,null);
                     
            }
        }catch(SQLException e){
            System.out.println("There is error in "+e.getMessage());
        }                    
        return tmp;
    }
    private boolean maxBookLimitError(String mStudentRFID){
        boolean tmp=false;
        try{
            psmnt=conn.prepareStatement("SELECT * FROM book_details a,name b"
                   + " WHERE a.borrower=b.student_id AND b.rfid_number=? LiMIT 11 ");
            psmnt.setString(1,mStudentRFID);
            mName=psmnt.executeQuery();
            int i=0;
            while(mName.next()){
                i=i+1;
            }
            if(i<9){
                tmp=true;
            }
        }catch(SQLException e){
            
        }     
        return tmp;
    }
}
