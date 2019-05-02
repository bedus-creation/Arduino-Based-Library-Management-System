package library;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import library.display.ShowReturned;
public class ShowReturn extends JFrame{
         int screenWidth,screenHeight;
         Image mBackroundmg;
         ShowReturned mShowReturned;
    public ShowReturn(String netID,String hostID,String mStudentRFID,int mTransactionID)
    {
         super("The following book is registered to the Following Student.");
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
         screenWidth = screenSize.width; 
         screenHeight = screenSize.height; 
         setSize(screenWidth,screenHeight);
         setVisible(true);
         setResizable(false);
         mShowReturned=new ShowReturned(this,netID,hostID,mStudentRFID,mTransactionID);
         add(mShowReturned);
         revalidate();
         repaint();
    }


    public static void main(String[]args)
    {
        ShowReturn app=new ShowReturn("123456555","010","35665555765485753484867112",1706);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}