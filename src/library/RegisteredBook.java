package library;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class RegisteredBook extends JFrame{
    int screenWidth,screenHeight;
    File mFile; 
    Image mBookImage;
    String mBookName,mBookID,mBookAuthor,mSection,mDate;
    public RegisteredBook(File File,String BookName,String BookID,String BookAuthor,String Section,String date){
        super("Registration Of Book");
        /* 
        *    Registeration of data to the class
        */
        this.mFile=File;
        this.mBookName=BookName;
        this.mBookID=BookID;
        this.mBookAuthor=BookAuthor;
        this.mSection=Section;
        this.mDate=date;
        
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
         screenWidth = screenSize.width; 
         screenHeight = screenSize.height; 
         setLocation(screenWidth/2-250,screenHeight/2-250);
         setSize(500,500);
         setVisible(true);
         
         
         /*
         *   Displaying the content
         */
         setLayout(null);
         ImageIcon image=new ImageIcon(mFile.getAbsolutePath());
         JLabel lImage=new JLabel(image);
         lImage.setBounds(screenWidth/2-image.getIconWidth()/2,10,image.getIconWidth(),image.getIconHeight());
         JLabel lBookName=new JLabel(mBookName);
         lBookName.setBounds(screenWidth/2-200,image.getIconHeight(),400,30);
         add(lImage);
         add(lBookName);
    }
}
