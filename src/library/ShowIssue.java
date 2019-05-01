package library;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import library.display.ShowIssuedBook;
public class ShowIssue extends JFrame{
         int screenWidth,screenHeight;
         Image mBackroundmg;
         ShowIssuedBook mShowIssuedBook;
    public ShowIssue(String netID,String hostID,String mStudentRFID,int mTransactionID)
    {
         super("The following book is registered to the Following Student.");
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
         screenWidth = screenSize.width; 
         screenHeight = screenSize.height; 
         setSize(screenWidth,screenHeight);
         setVisible(true);
         setResizable(false);
         mShowIssuedBook=new ShowIssuedBook(this,netID,hostID,mStudentRFID,mTransactionID);
         add(mShowIssuedBook);
         revalidate();
         repaint();
    }

    public static void main(String[]args)
    {
        ShowIssue app=new ShowIssue("123456555","011","35665555765485753484867549",1706);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}