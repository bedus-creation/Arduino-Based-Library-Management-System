package library;

import Install.StartDatabaseEngine;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame {
    int screenWidth,screenHeight;
    JLabel l1,j_background;
    LoginPage  mLoginPage;
    Home mHome;
    RegisterStudent mRegisterStudent;
    RegisterBook mRegisterBook;
    SearchStudent mSearchStudent;
    SearchBook mSearchBook;
    public Main(){
         super("Library System");
         //initializing all the panel to make easier to add 
         // and remove the panel
         StartDatabaseEngine m=new StartDatabaseEngine();
         mLoginPage=new LoginPage(this);
         mRegisterStudent=new RegisterStudent(this);
         mRegisterBook=new RegisterBook(this);
         mSearchStudent=new SearchStudent(this);
         mSearchBook=new SearchBook(this);
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
         screenWidth = screenSize.width; 
         screenHeight = screenSize.height; 
         setSize(screenWidth,screenHeight);
         setResources(mLoginPage);
    }
    public void loadDesktop(){
        
    }
    public void setResources(JPanel mPanel){
        add(mPanel);
    }
    public static void main(String []args){
        Main apps=new Main();
        apps.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        apps.setVisible(true);
        apps.setResizable(false);       
    }
}
