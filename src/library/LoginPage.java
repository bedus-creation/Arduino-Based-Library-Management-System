package library;

import database.EllewLogin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class LoginPage extends JPanel{
    Main frame;
    JLabel  j_TopText;
    JButton b1,mLogin;
    JTextField username;
    JPasswordField password;
    int screenWidth,screenHeight;
    private Image mBackroundmg;
    Dimension screenSize;
    public LoginPage(Main mFrame){
        this.frame=mFrame;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
        screenWidth = screenSize.width; 
        screenHeight = screenSize.height; 
        setSize(screenWidth,screenHeight);
        setLayout(null);
        username=new JTextField("username");
        username.setBounds(screenWidth/2-280,300,200,30);
        add(username);
        password=new JPasswordField("hidden text",15);
        password.setBounds(screenWidth/2-60,300,200,30);
        password.setText("password");
        mLogin=new JButton("Login");
        mLogin.setBounds(screenWidth/2+170,300,100,30);
        add(mLogin);
        add(username);
        add(password);
        
        /* Adding the key listener to the JTextField */
        HoverField mHover=new HoverField();
        username.addKeyListener(mHover);
        password.addKeyListener(mHover);
        //now adding the action listener
        HandleEvent handle=new HandleEvent();
        username.addActionListener(handle);
        password.addActionListener(handle);
        mLogin.addActionListener(handle);
    }
    public void paintComponent(Graphics g) {
        ImageIcon iconBackground=new ImageIcon(getClass().getResource("/img/image-5.jpg"));
        mBackroundmg=iconBackground.getImage();
        g.drawImage(mBackroundmg, 0, 0, null);
        g.setColor(Color.red);
        g.setFont(new Font("New Times Roman", Font.BOLD,12));
        g.drawString("\"Technical education for economic growth\"",screenSize.width/2-150,50);
        g.setColor(Color.GREEN);
        g.setFont(new Font("New Times Roman", Font.BOLD,40));
        g.drawString("Purwanchal Engineering Campus",screenSize.width/2-350,100);
        g.setFont(new Font("New Times Roman", Font.PLAIN,30));
        g.drawString("Dharan-8 , Tinkune ",screenSize.width/2-150,170);
        File fileLogo=new File("src\\img\\tu-logo.png");
        ImageIcon logoIcon=new ImageIcon(fileLogo.getAbsolutePath());
        mBackroundmg=logoIcon.getImage();
        g.drawImage(mBackroundmg,screenSize.width/2-300,100,null); 
        g.drawImage(mBackroundmg,screenSize.width/2+150,100,null);
        g.drawLine(screenSize.width/2-250,250,screenSize.width/2+200,250);
     }
    private class HoverField implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getSource()==username){
                String u_name=username.getText();
                if(u_name.equals("username")){
                    username.setText("");
                }else{
                    
                }
            }else if(e.getSource()==password){
                String p_name=password.getText();
                if(p_name.equals("password")){
                    password.setText("");
                }else{
                    
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getSource()==username){
                String u_name=username.getText();
                if(u_name.isEmpty()){
                    username.setText("username");
                }
            }else if(e.getSource()==password){
                String p_name=password.getText();
                if(p_name.isEmpty()){
                    password.setText("password");
                }
            }  
        }
        
    }
    private class HandleEvent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==username){
                JOptionPane.showMessageDialog(LoginPage.this,"this is the about","about",JOptionPane.PLAIN_MESSAGE); 
            }
            else if(e.getSource()==password){
                
            }
            else if(e.getSource()==mLogin){
                String mUsername=username.getText();
                String mPassword=password.getText();
                EllewLogin ellewLogin=new EllewLogin(mUsername,mPassword);
                if(!ellewLogin.getLoginStatus()){
                     frame.mHome=new Home(frame);
                     frame.remove(frame.mLoginPage);
                     frame.add(frame.mHome);
                     frame.revalidate();
                     frame.repaint();
                }else{  //username and password is not valid.................
                    JOptionPane.showMessageDialog(LoginPage.this,"Your username or password is not valid.","about",JOptionPane.PLAIN_MESSAGE); 
                  }
            }
            else{
                
            }
        }
    }
}
