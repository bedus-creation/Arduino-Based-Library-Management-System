package library;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import library.interrupte.RegisterBookFormFromInterrupte;

public class BookIssueFormInterrupt extends JFrame{
    int screenWidth,screenHeight;
    RegisterBookFormFromInterrupte mRegisterBookFormFromInterrupte;
    public BookIssueFormInterrupt(String netID,String hostID){
         super("Not registered yet.");
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
         screenWidth = screenSize.width; 
         screenHeight = screenSize.height; 
         setLocation(screenWidth/2-300,screenHeight/2-350);
         setSize(600,700);
         setVisible(true);
         /* 
         *
         *   Initialization of the varible...............
         */
         mRegisterBookFormFromInterrupte=new RegisterBookFormFromInterrupte(this,netID,hostID);
         add(mRegisterBookFormFromInterrupte);
         revalidate();
         repaint();
    }
}
