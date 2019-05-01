package library;

import database.EllewInsertBookIntoDatabase;
import database.GenerateNetIDForNewBook;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterBook extends JPanel{    
Main mFrame;    
private Image mBackroundmg;
JLabel nBookName,nBookID,nBookAuthor,nBookSection,nBookCover,nNumberOfBook,nBookSubmit;
JTextField fBookName,fBookID,fBookAuthor,fBookSection,fNumberOfBook;
JButton browsePhoto,bSubmit;
int screenWidth,screenHeight;
//Registering the JBUtton for Menu
String mBookID,mBookAuthor,mBookName,mBookSection;
int mNumberOfBook;
File mFile;
GenerateNetIDForNewBook mGenerateNetIDForNewBook;
Dimension screenSize;
public RegisterBook(Main mFrame){
    setLayout(null); 
    this.mFrame=mFrame;
    screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    screenWidth = screenSize.width; 
    screenHeight = screenSize.height;
    
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
                   MTRegisterBook.setEnabled(false);
                   
                   /* Adding action to the Register Student */
                   MTRegisterStudent.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent e){
                           mFrame.remove(mFrame.mRegisterBook);
                           mFrame.add(mFrame.mRegisterStudent);
                           mFrame.revalidate();
                           mFrame.repaint();
                       }
                   });
                   /*
                   *  Adding action to the Home 
                   */
                   MTHome.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent e){
                        mFrame.remove(mFrame.mRegisterBook);
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
     mFile=new File("");
    
    
    /*Initialization of JLabel */
    nBookID= new JLabel("Book ID :");
    nBookName=new JLabel("Book Name :");
    nBookAuthor=new JLabel("Book Author :");
    nBookSection=new JLabel("Book Section :");
    nBookCover=new JLabel("Book Cover:");
    nNumberOfBook=new JLabel("Total Book:"); 
    nBookSubmit=new JLabel("Submit");
    
    /*Initialization of JTextField */
    setFontJLabel(nBookID);
    setFontJLabel(nBookName);
    setFontJLabel(nBookAuthor);
    setFontJLabel(nBookSection);
    setFontJLabel(nBookCover);
    setFontJLabel(nNumberOfBook);
    setFontJLabel(nBookSubmit);
    /*setting bounds for Jlabel */
    nBookID.setBounds(screenWidth/2-250,350,200,30);
    nBookName.setBounds(screenWidth/2-250,400,200,30);
    nBookAuthor.setBounds(screenWidth/2-250,450,300,30);
    nBookSection.setBounds(screenWidth/2-250,500,200,30);
    nNumberOfBook.setBounds(screenWidth/2-250,550,200,30);
    nBookCover.setBounds(screenWidth/2-250,600,200,30);
    nBookSubmit.setBounds(screenWidth/2-250,650,200,30);
    /* Adding the JLabel */
    add(nBookID);
    add(nBookName);
    add(nBookAuthor);
    add(nBookSection);
    add(nNumberOfBook);
    add(nBookCover);
    add(nBookSubmit);
    /* Initilization of JTextField */
    fBookID=new JTextField("eg. Bedu Tamang ",20);
    mGenerateNetIDForNewBook=new GenerateNetIDForNewBook();
    fBookID.setText(mGenerateNetIDForNewBook.getNetID());
    fBookName=new JTextField("",20);
    fBookAuthor=new JTextField("",20);
    fBookSection=new JTextField("",20);
    fNumberOfBook=new JTextField("",20);
    browsePhoto=new JButton("Student Photo");
    bSubmit=new JButton("Click Here");
    //bounding the field 
    fBookID.setBounds(screenWidth/2,350,200,30);
    fBookName.setBounds(screenWidth/2,400,200,30);
    fBookAuthor.setBounds(screenWidth/2,450,200,30);
    fBookSection.setBounds(screenWidth/2,500,200,30);
    fNumberOfBook.setBounds(screenWidth/2,550,200,30);
    browsePhoto.setBounds(screenWidth/2,600,200,30);
    bSubmit.setBounds(screenWidth/2, 650,200,30);
    /* Adding the fields*/
    add(fBookID);
    add(fBookName);
    add(fBookAuthor);
    add(fBookSection);
    add(fNumberOfBook);
    add(browsePhoto);
    add(bSubmit);
    
    

    //adding actionListener 
    RegisterStudentEventHandeller mListener=new RegisterStudentEventHandeller();
    browsePhoto.addActionListener(mListener);
    bSubmit.addActionListener(mListener);
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
        g.drawString("Enter Book  Details",screenSize.width/2-150,300);
   }
    private class RegisterStudentEventHandeller implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==browsePhoto){
                mBookID=fBookID.getText();
                mBookName=fBookName.getText().toString();
                mBookAuthor=fBookAuthor.getText().toString();
                mBookSection=fBookSection.getText().toString();
                String temp=fNumberOfBook.getText().toString();
                mNumberOfBook=Integer.parseInt(temp);
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
                try {
                    mFile = new File(mediaUrl.toURI());
                } catch (URISyntaxException ex) {
                    Logger.getLogger(RegisterStudent.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }if(e.getSource()==bSubmit){
                    new EllewInsertBookIntoDatabase(mBookName,mBookID,mBookAuthor,mBookSection,mNumberOfBook,mFile);
                    System.out.println(mFile);
            }//end of if section
        }
    }
}
