/*
*  This is almost Done ! 
*/

package library;

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
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterStudent extends JPanel{    
Main mFrame;    
private Image mBackroundmg;
JLabel lName,lStudentId,lDOB,lAddress,lContact,lStudentRFIDNumber,lStudentImage;
JTextField fName,fStudentId,fDOB,fAddress,fContact,fStudentRFIDNumber;
JButton browsePhoto;
int screenWidth,screenHeight;
//Registering the JBUtton for Menu
String accountHolder,student_id,mDob,
        rfid_number,mAddress,gender,
        mContact;
Dimension screenSize;
public RegisterStudent(Main mFrame){
    setLayout(null); 
    this.mFrame=mFrame;
    screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    screenWidth = screenSize.width; 
    screenHeight = screenSize.height;
    RegisterStudentEventHandeller mListener=new RegisterStudentEventHandeller();
    
    /*
    *   setting the menus 
    */
    JDesktopPane theDesktop=new JDesktopPane();
        JMenuBar bar=new JMenuBar();
        JMenu JMmenu=new JMenu("Menu");
        JMenu JMAbout=new JMenu("About");
            JMenuItem MTHome=new JMenuItem("Home");  /* MeniItem of Menu */
            JMenuItem MTExit =new JMenuItem("Exit"); /* MenuItem of Menu */
            JMenuItem MTRegisterStudent=new JMenuItem("Register Student");
            JMenuItem MTRegisterBook=new JMenuItem("Register Book");
            JMenuItem MTSearchStudent=new JMenuItem("Search Student");
            JMenuItem MTSearchBook =new JMenuItem("Search Book");
                  MTHome.setMnemonic('H');
                  MTExit.setMnemonic('E');
                    /*
                    *   Adding Action to the Exit
                    */
                   MTExit.addActionListener(
                        new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                System.exit(1);
                            }  
                        });
                   /* Adding action to Register Book */
                   MTRegisterBook.addActionListener(
                       new ActionListener(){
                           public void actionPerformed(ActionEvent e){
                               mFrame.remove(mFrame.mRegisterStudent);
                               mFrame.add(mFrame.mRegisterBook);
                               mFrame.revalidate();
                               mFrame.repaint();
                           }
                       });
                   /*
                   *  Adding action to the Home 
                   */
                   MTHome.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent e){
                        mFrame.remove(mFrame.mRegisterStudent);
                        mFrame.add(mFrame.mHome);
                        mFrame.revalidate();
                        mFrame.repaint();
                       }
                   });
                  JMmenu.add(MTHome);
                  JMmenu.add(MTExit);
                  JMmenu.add(MTSearchStudent);
                  JMmenu.add(MTSearchBook);
                  JMmenu.add(MTRegisterStudent);
                  JMmenu.add(MTRegisterBook);
               bar.add(JMmenu);
               bar.add(JMAbout);
               bar.setLocation(0,10);
               bar.setBounds(0,0,screenWidth,30);
            add(bar);
        add(theDesktop);
    setLayout(null);  
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
    lName.setBounds(screenWidth/2-250,350,200,30);
    lStudentId.setBounds(screenWidth/2-250,400,200,30);
    lDOB.setBounds(screenWidth/2-250,450,300,30);
    lAddress.setBounds(screenWidth/2-250,500,200,30);
    lContact.setBounds(screenWidth/2-250,550,200,30);
    lStudentRFIDNumber.setBounds(screenWidth/2-250,600,200,30);
    lStudentImage.setBounds(screenWidth/2-250,650,200,30);

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
    fStudentRFIDNumber=new JTextField("",20);
    browsePhoto=new JButton("Student Photo");
    //bounding the field 
    fName.setBounds(screenWidth/2,350,200,30);
    fStudentId.setBounds(screenWidth/2,400,200,30);
    fDOB.setBounds(screenWidth/2,450,200,30);
    fAddress.setBounds(screenWidth/2,500,200,30);
    fContact.setBounds(screenWidth/2,550,200,30);
    fStudentRFIDNumber.setBounds(screenWidth/2,600,200,30);
    browsePhoto.setBounds(screenWidth/2,650,200,30);
    /* Adding the fields*/
    add(fName);
    add(fStudentId);
    add(fDOB);
    add(fAddress);
    add(fContact);
    add(fStudentRFIDNumber);
    add(browsePhoto);
    
    
    //adding actionListener 
    browsePhoto.addActionListener(mListener);
}
public void setFontJLabel(JLabel mLabel){
    Font font = new Font("Courier", Font.BOLD,20);
    mLabel.setForeground(Color.WHITE);
    mLabel.setFont(font);
}
public void paintComponent(Graphics g) {
    
        ImageIcon iconBackground=new ImageIcon(getClass().getResource("/img/image-5.jpg"));
        mBackroundmg=iconBackground.getImage();
        g.drawImage(mBackroundmg, 0, 0, null);     
        
        g.setColor(Color.red);
        g.setFont(new Font("New Times Roman", Font.BOLD,12));
        g.drawString("\"Technical education for economic growth\"",screenSize.width/2-150,50);
        g.setColor(Color.GREEN);
        g.setFont(new Font("New Times Roman", Font.BOLD,40));
        g.drawString("Purwanchal Engineering Campus",screenSize.width/2-350,100);
        g.setFont(new Font("New Times Roman", Font.PLAIN,30));
        g.drawString("Dharan-8 , Tinkune ",screenSize.width/2-150,170);
        ImageIcon logoIcon=new ImageIcon(getClass().getResource("/img/tu-logo.png"));
        mBackroundmg=logoIcon.getImage();
        g.drawImage(mBackroundmg,screenSize.width/2-300,100,null); 
        g.drawImage(mBackroundmg,screenSize.width/2+150,100,null);
        g.drawLine(screenSize.width/2-250,250,screenSize.width/2+200,250);
        g.drawLine(screenSize.width/2-250,330,screenSize.width/2+200,330);
        g.drawString("Enter Student Details",screenSize.width/2-150,300);
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
                EllewInsertData mS=new EllewInsertData(accountHolder,student_id,mDob,rfid_number,
                                    mAddress,gender,mContact,mFile);
                if(mS.getAcknowledge()){
                    fName.setText("");
                    fName.setText("");
                    fStudentId.setText("");
                    fDOB.setText("");
                    fAddress.setText("");
                    fContact.setText("");
                    fStudentRFIDNumber.setText("");
                     JOptionPane.showMessageDialog(mFrame,"Successfully Registered",
                            "Sucecss",JOptionPane.INFORMATION_MESSAGE,beg1);
                }
                System.out.println(mFile);
            }//end of if section
        }
    }
}
