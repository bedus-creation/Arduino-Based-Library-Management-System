package library.display;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class BookNotIssuedErrorFrame extends JFrame{
    int screenWidth,screenHeight;
    BookNotIssuedErrorPanel mBookNotIssuedErrorPanel;
    public BookNotIssuedErrorFrame(String netID,String hostID){
         super("Not registered yet.");
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
         screenWidth = screenSize.width; 
         screenHeight = screenSize.height; 
         setLocation(screenWidth/2-300,screenHeight/2-350);
         setSize(600,700);
         setVisible(true);
         mBookNotIssuedErrorPanel=new BookNotIssuedErrorPanel(netID,hostID);
         add(mBookNotIssuedErrorPanel);
         revalidate();
         repaint();
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    }
    public static void main(String [] args){
        BookNotIssuedErrorFrame mBookNotIssuedErrorFrame=new BookNotIssuedErrorFrame("0000000001","001");
        
    }
}
