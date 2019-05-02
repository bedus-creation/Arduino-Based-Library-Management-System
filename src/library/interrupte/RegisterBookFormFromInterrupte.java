/*
*
*
*/
package library.interrupte;

import database.EllewInsertBookIntoDatabase;
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

public class RegisterBookFormFromInterrupte extends JPanel{
    JFrame frame;
    private Image mBackroundmg;
    JLabel nBookName,nBookID,nBookAuthor,nBookSection,nBookCover,nBookSubmit;
    JTextField fBookName,fBookID,fBookAuthor,fBookSection;
    JButton browsePhoto,bSubmit;
    int screenWidth,screenHeight;
    //Registering the JBUtton for Menu
    //JButton bHome,bRegisterBook;
    String mBookID,mBookAuthor,mBookName,mBookSection;
    File mFile;
public RegisterBookFormFromInterrupte(JFrame mFrame,String netID,String hostID){
    setLayout(null); 
    this.frame=mFrame;
    mFile=null;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    screenWidth = screenSize.width; 
    screenHeight = screenSize.height;
    /*
    *  Initialization of the String Value
    */
    
    
    /*Initialization of JLabel */
    nBookID= new JLabel("Book ID :");
    nBookName=new JLabel("Book Name :");
    nBookAuthor=new JLabel("Book Author :");
    nBookSection=new JLabel("Book Section :");
    nBookCover=new JLabel("Book Cover:");
    nBookSubmit=new JLabel("Submit");
    
    /*Initialization of JTextField */
    setFontJLabel(nBookID);
    setFontJLabel(nBookName);
    setFontJLabel(nBookAuthor);
    setFontJLabel(nBookSection);
    setFontJLabel(nBookCover);
    setFontJLabel(nBookSubmit);
    /*setting bounds for Jlabel */
    nBookID.setBounds(50,200,200,30);
    nBookName.setBounds(50,250,200,30);
    nBookAuthor.setBounds(50,300,300,30);
    nBookSection.setBounds(50,350,200,30);
    nBookCover.setBounds(50,400,200,30);
    nBookSubmit.setBounds(50,450,200,30);
    /* Adding the JLabel */
    add(nBookID);
    add(nBookName);
    add(nBookAuthor);
    add(nBookSection);
    add(nBookCover);
    add(nBookSubmit);
    /* Initilization of JTextField */
    fBookID=new JTextField(mBookID,20);
    fBookID.setEditable(false);
    fBookName=new JTextField("",20);
    fBookAuthor=new JTextField("",20);
    fBookSection=new JTextField("",20);
    browsePhoto=new JButton("Student Photo");
    bSubmit=new JButton("Click Here");
    //bounding the field 
    fBookID.setBounds(300,200,200,30);
    fBookName.setBounds(300,250,200,30);
    fBookAuthor.setBounds(300,300,200,30);
    fBookSection.setBounds(300,350,200,30);
    browsePhoto.setBounds(300,400,200,30);
    bSubmit.setBounds(300,450,200,30);
    /* Adding the fields*/
    add(fBookID);
    add(fBookName);
    add(fBookAuthor);
    add(fBookSection);
    add(browsePhoto);
    add(bSubmit);
    /*for menu 
    *
    bHome=new JButton("Home");
    bRegisterBook=new JButton("Register Book");
    //
    bHome.setBounds(screenWidth/2-550,200,200,30);
    bRegisterBook.setBounds(screenWidth/2-550,250,200,30);
    //adding the menu list to the JPanel
    add(bHome);
    add(bRegisterBook);
    *
    */
    //adding actionListener 
    RegisterBookEventHandeller mListener=new RegisterBookEventHandeller();
    browsePhoto.addActionListener(mListener);
    bSubmit.addActionListener(mListener);
    //now adding the menu to actionListener
    //bHome.addActionListener(mListener);
    }
public void setFontJLabel(JLabel mLabel){
    Font font = new Font("Courier", Font.BOLD,20);
    mLabel.setForeground(Color.WHITE);
    mLabel.setFont(font);
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
    g.drawString("Register Book",150,100);
   }
private class RegisterBookEventHandeller implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==browsePhoto){
                URL mediaUrl=null;
                JFileChooser mBookCoverChooser=new JFileChooser();
                int resultBookChooser;
                resultBookChooser = mBookCoverChooser.showOpenDialog(null);
                if(resultBookChooser==JFileChooser.APPROVE_OPTION){
                      try{
                           mediaUrl=mBookCoverChooser.getSelectedFile().toURI().toURL();
                        }catch(MalformedURLException malformedURLException){
                           System.err.println("error");
                         }
                   }
                try {
                    mFile = new File(mediaUrl.toURI());
                } catch (URISyntaxException ex) {
                    Logger.getLogger(RegisterStudent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if(e.getSource()==bSubmit){
                mBookID=fBookID.getText().toString();
                mBookName=fBookName.getText().toString();
                mBookAuthor=fBookAuthor.getText().toString();
                mBookSection=fBookSection.getText().toString();
               // new EllewInsertBookIntoDatabase(mBookName,netID,hostID,mBookAuthor,mBookSection,mFile);
                System.out.println(mFile);
            }
        }
    }    
}
