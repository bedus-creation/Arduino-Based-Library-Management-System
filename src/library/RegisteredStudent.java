package library;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
public class RegisteredStudent extends JFrame{
    int screenWidth,screenHeight;
    public RegisteredStudent(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
         screenWidth = screenSize.width; 
         screenHeight = screenSize.height; 
         setLocation(screenWidth/2-250,screenHeight/2-250);
         setSize(500,500);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setVisible(true);
    }
    public static void main(String [] args){
        JFrame app=new RegisteredStudent();
        
    }
}
