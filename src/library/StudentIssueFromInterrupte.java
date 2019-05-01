
package library;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import library.interrupte.RegisterStudentFormFromInterpte;

public class StudentIssueFromInterrupte extends JFrame{
    int screenWidth,screenHeight;
    RegisterStudentFormFromInterpte mRegisterStudentFormFromInterpte;
    public StudentIssueFromInterrupte(String mStudentID){
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
         mRegisterStudentFormFromInterpte=new RegisterStudentFormFromInterpte(this,mStudentID);
         add(mRegisterStudentFormFromInterpte);
         revalidate();
         repaint();
    }
}
