package library;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class ShowBook extends JFrame{
    int screenWidth,screenHeight;
    
    public ShowBook(String mBookName,String mBookID,String mBookAuthor){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
         screenWidth = screenSize.width; 
         screenHeight = screenSize.height; 
         setLocation(screenWidth/2-250,screenHeight/2-250);
         setSize(500,500);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setVisible(true);
    }
}
