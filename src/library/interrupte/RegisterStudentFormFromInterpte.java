package library.interrupte;

import database.EllewInsertData;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import library.RegisterStudent;

public class RegisterStudentFormFromInterpte extends JPanel{
JFrame mFrame;    
private Image mBackroundmg;
JLabel lName,lStudentId,lDOB,lAddress,lContact,lStudentRFIDNumber,lStudentImage;
JTextField fName,fStudentId,fDOB,fAddress,fContact,fStudentRFIDNumber;
JButton browsePhoto;
int screenWidth,screenHeight;
//Registering the JBUtton for Menu
JButton bSubmit;
String accountHolder,student_id,mDob,
        rfid_number,mAddress,gender,
        mContact;
    public RegisterStudentFormFromInterpte(JFrame mFrame,String mStudentID){
    setLayout(null); 
    this.mFrame=mFrame;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    screenWidth = screenSize.width; 
    screenHeight = screenSize.height;
    /*
    *  Initialization of the String Value
    */
    gender="male";
    /*Initialization of JLabel */
    lName=new JLabel("Student Name :");
    lStudentId=new JLabel("StudentId :");
    lDOB=new JLabel("Date of Birth :");
    lAddress=new JLabel("Address :");
    lContact=new JLabel("Contact Number :");
    lStudentRFIDNumber=new JLabel("RFID number :");
    lStudentImage=new JLabel("Student Image :");
    
    
    /*Initialization of JTextField */
    setFontJLabel(lName);
    setFontJLabel(lStudentId);
    setFontJLabel(lDOB);
    setFontJLabel(lAddress);
    setFontJLabel(lContact);
    setFontJLabel(lStudentRFIDNumber);
    setFontJLabel(lStudentImage);
    
    /*setting bounds for Jlabel */
    lName.setBounds(50,200,200,30);
    lStudentId.setBounds(50,250,200,30);
    lDOB.setBounds(50,300,300,30);
    lAddress.setBounds(50,350,200,30);
    lContact.setBounds(50,400,200,30);
    lStudentRFIDNumber.setBounds(50,450,200,30);
    lStudentImage.setBounds(50,500,200,30);

    /* Adding the JLabel */
    add(lName);
    add(lStudentId);
    add(lDOB);
    add(lAddress);
    add(lContact);
    add(lStudentRFIDNumber);
    add(lStudentImage);
    /* Initilization of JTextField */
    fName=new JTextField("eg. Bedu Tamang ",20);
    fStudentId=new JTextField("",20);
    fDOB=new JTextField("",20);
    fAddress=new JTextField("",20);
    fContact=new JTextField("",20);
    fStudentRFIDNumber=new JTextField(mStudentID,20);
    fStudentRFIDNumber.setEditable(false);
    browsePhoto=new JButton("Student Photo");
    //bounding the field 
    fName.setBounds(300,200,200,30);
    fStudentId.setBounds(300,250,200,30);
    fDOB.setBounds(300,300,200,30);
    fAddress.setBounds(300,350,200,30);
    fContact.setBounds(300,400,200,30);
    fStudentRFIDNumber.setBounds(300,450,200,30);
    browsePhoto.setBounds(300,500,200,30);
    /* Adding the fields*/
    add(fName);
    add(fStudentId);
    add(fDOB);
    add(fAddress);
    add(fContact);
    add(fStudentRFIDNumber);
    add(browsePhoto);
    
    //adding actionListener 
    RegisterStudentEventHandeller mListener=new RegisterStudentEventHandeller();
    browsePhoto.addActionListener(mListener);
    
}
public void paintComponent(Graphics g) {
    File iBackground=new File("src\\img\\image-5.jpg");
    ImageIcon iconBackground=new ImageIcon(iBackground.getAbsolutePath());
    mBackroundmg=iconBackground.getImage();
    g.drawImage(mBackroundmg, 0, 0, null);
    File fileLogo=new File("src\\img\\tu-logo.png");
    ImageIcon logoIcon=new ImageIcon(fileLogo.getAbsolutePath());
    mBackroundmg=logoIcon.getImage();
    g.drawImage(mBackroundmg,20,30,null);
    g.setColor(Color.YELLOW);
    g.setFont(new Font("Courier", Font.BOLD,40));
    g.drawString("Register Student",150,100);
   }    
public void setFontJLabel(JLabel mLabel){
    Font font = new Font("Courier", Font.BOLD,20);
    mLabel.setForeground(Color.WHITE);
    mLabel.setFont(font);

    }
private class RegisterStudentEventHandeller implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==browsePhoto){
                accountHolder=fName.getText().toString();
                student_id=fStudentId.getText().toString();
                rfid_number=fStudentRFIDNumber.getText().toString();
                mDob=fDOB.getText().toString();
                mAddress=fAddress.getText().toString();
                mContact=fContact.getText().toString();
                URL mediaUrl=null;
                JFileChooser file=new JFileChooser();
                int result=file.showOpenDialog(null);
                if(result==JFileChooser.APPROVE_OPTION){
                      try{
                           mediaUrl=file.getSelectedFile().toURI().toURL();
                        }catch(MalformedURLException malformedURLException){
                           System.err.println("error");
                         }
                      }   
                Icon beg1=new ImageIcon(mediaUrl);
                JLabel browse_photo_replace=new JLabel(beg1);
                browse_photo_replace.setBounds(screenWidth/2+250,100,200,200);
                add(browse_photo_replace);
                repaint();
                File mFile=new File("");
                try {
                    mFile = new File(mediaUrl.toURI());
                } catch (URISyntaxException ex) {
                    Logger.getLogger(RegisterStudent.class.getName()).log(Level.SEVERE, null, ex);
                }
                new EllewInsertData(accountHolder,student_id,mDob,rfid_number,
                                    mAddress,gender,mContact,mFile);
                System.out.println(mFile);
            }//end of if section
        }
    }
}
