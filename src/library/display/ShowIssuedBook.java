package library.display;

import database.EllewBookBrowserIntoDatabase;
import database.EllewRetriveBookDetailFromDatabase;
import database.EllewRetriveStudentDetailFromDatabase;
import database.EllewUpdateState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ShowIssuedBook extends JPanel{
    Image mBackroundmg;
    File mStudentImageFile,mBookImageFile;
    EllewRetriveBookDetailFromDatabase mEllewRetriveBookDetailFromDatabase;
    EllewRetriveStudentDetailFromDatabase mEllewRetriveStudentDetailFromDatabase;
    ImageIcon mBookImageIcon,mStudentImageIcon;
    String mStudentName,mStudentAddress,
           mStudentRFID,mBookName,mBookID,mBookAuthor,mBookLocation,mStudentID;
    int mTransactionID;
    Dimension screenSize;
    JTextField JTStudentName,JTStudentID,JTStudentAddress,JTStudentRFID;
    JTextField JTBookName,JTBookAuthor,JTBookID,JTBookLocation;
    public ShowIssuedBook(JFrame mFrame,String netID,String hostID,String mStudentRFID,int mTransactionID){
       setLayout(null); 
       screenSize=Toolkit.getDefaultToolkit().getScreenSize();
       mEllewRetriveBookDetailFromDatabase=new EllewRetriveBookDetailFromDatabase();
       mEllewRetriveBookDetailFromDatabase.searchUsingBookID(netID+hostID);
       mEllewRetriveStudentDetailFromDatabase=new EllewRetriveStudentDetailFromDatabase();
       mEllewRetriveStudentDetailFromDatabase.searchUsingRFID(mStudentRFID);
       mStudentImageIcon=new ImageIcon(mEllewRetriveStudentDetailFromDatabase.getStudentImageLocation());
       mBookImageIcon=new ImageIcon(mEllewRetriveBookDetailFromDatabase.getBookCoverImagLocation());
       this.mBookID=netID+hostID;
       this.mTransactionID=mTransactionID;
       
       /* Initilization of the variable to student */
       mStudentName=mEllewRetriveStudentDetailFromDatabase.getStudentName();
       mStudentID=mEllewRetriveStudentDetailFromDatabase.getStudentID();
       mStudentAddress=mEllewRetriveStudentDetailFromDatabase.getStudentAddress();
       
       /* Initilizaiton of Book variable */
       mBookName=mEllewRetriveBookDetailFromDatabase.getBookName();
       mBookAuthor=mEllewRetriveBookDetailFromDatabase.getBookAuthor();
       mBookLocation=mEllewRetriveBookDetailFromDatabase.getBookSection();
       
       
       
       JButton jbConfirm=new JButton("yes");
       jbConfirm.requestFocusInWindow();
       jbConfirm.setBounds(screenSize.width/2+50,230,70,30);
       add(jbConfirm);
       jbConfirm.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
               EllewBookBrowserIntoDatabase mEllewBookBrowserIntoDatabase=new EllewBookBrowserIntoDatabase();
               mEllewBookBrowserIntoDatabase.InsertBookBorrowerIntoDatabase(netID, hostID, mStudentID);
               new EllewUpdateState(mTransactionID);
               JOptionPane.showConfirmDialog(mFrame,"The Book is successfully Issued.","Issued",JOptionPane.PLAIN_MESSAGE);
               mFrame.dispose();
           }
       });
       JButton jbCancel=new JButton("No");
       jbCancel.setBounds(screenSize.width/2+130,230,70,30);
       add(jbCancel);
       jbCancel.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
               /* if the user say no then transaction status is deleted */
               new EllewUpdateState(mTransactionID);
               JOptionPane.showConfirmDialog(mFrame,"This transaction is deleted.","You Said error.",JOptionPane.PLAIN_MESSAGE);
               mFrame.dispose();
           }
       }); 
       /* adding the student details */
        JTStudentName=new JTextField(mStudentName);
        JTStudentID  =new JTextField(mStudentID);
        JTStudentAddress=new JTextField(mStudentAddress);
        JTStudentRFID=new JTextField(mStudentRFID);
        
        /* The Book details textField */
        JTBookName =new JTextField(mBookName);
        JTBookID=new JTextField(mBookID);
        JTBookAuthor=new JTextField(mBookAuthor);
        JTBookLocation=new JTextField(mBookLocation);
        
        
        /* Positioning the componenet into the panel */
        JTStudentName.setBounds(screenSize.width/2-250,520,200,30);
        JTStudentID.setBounds(screenSize.width/2-250,550,200,30);
        JTStudentAddress.setBounds(screenSize.width/2-250,580,200,30);
        JTStudentRFID.setBounds(screenSize.width/2-250,610,200,30);
        
        JTBookName.setBounds(screenSize.width/2,520,200,30);
        JTBookID.setBounds(screenSize.width/2,550,200,30);
        JTBookAuthor.setBounds(screenSize.width/2,580,200,30);
        JTBookLocation.setBounds(screenSize.width/2,610,200,30);
       
        /* Setting up the font and color for the component */
        setFontJTextField(JTStudentName);
        setFontJTextField(JTStudentID);
        setFontJTextField(JTStudentAddress);
        setFontJTextField(JTStudentRFID);
        
        setFontJTextField(JTBookName);
        setFontJTextField(JTBookID);
        setFontJTextField(JTBookAuthor);
        setFontJTextField(JTBookLocation);
        
        add(JTStudentID);
        add(JTStudentAddress);
        add(JTStudentRFID);
        add(JTStudentName);
        add(JTStudentAddress);
        add(JTStudentRFID);
        
        add(JTBookName);
        add(JTBookID);
        add(JTBookAuthor);
        add(JTBookLocation);
        
    }
    private void setFontJTextField(JTextField p){
        p.setEditable(false);
        //p.setOpaque(false);
        p.setForeground(Color.BLUE);
        p.setFont(new Font(Font.SERIF,Font.BOLD,20));
    }
    public void paintComponent(Graphics g) {
    ImageIcon iconBackground=new ImageIcon(getClass().getResource("/img/image-5.jpg"));
        g.drawImage(iconBackground.getImage(), 0, 0, null);     
        
        g.setColor(Color.red);
        g.setFont(new Font("New Times Roman", Font.BOLD,12));
        g.drawString("\"Technical education for economic growth\"",screenSize.width/2-150,50);
        g.setColor(Color.GREEN);
        g.setFont(new Font("New Times Roman", Font.BOLD,40));
        g.drawString("Purwanchal Engineering Campus",screenSize.width/2-350,100);
        g.setFont(new Font("New Times Roman", Font.PLAIN,30));
        g.drawString("Dharan-8 , Tinkune ",screenSize.width/2-150,170);
        ImageIcon logoIcon=new ImageIcon(getClass().getResource("/img/tu-logo.png"));
        g.drawImage(logoIcon.getImage(),screenSize.width/2-500,0,null); 
        g.drawImage(logoIcon.getImage(),screenSize.width/2+300,0,null);
        g.drawLine(screenSize.width/2-250,200,screenSize.width/2+200,200);
        g.drawLine(screenSize.width/2-250,280,screenSize.width/2+200,280);
        g.drawString("sure about this Issue ?",screenSize.width/2-250,250);

    
       g.drawImage(mStudentImageIcon.getImage(),screenSize.width/2-250,300,200,200,null);
    
       g.drawImage(mBookImageIcon.getImage(),screenSize.width/2,300,200,200,null);
    
    }
}
