package library;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
public class Home extends JPanel{
    Main mFrame;
    UpdateDatabase mCheckDatabase;
    int screenWidth,screenHeight;
    private Image mBackroundmg;
    JLabel j_background,j_image1,j_image2,JLSearchStudent,JLSearchBook,JLSetting,JLLogout;
    JButton jb_image1,b_image2,JBSearchStudent,JBSearchBook,JBSetting,JBLogout;
    ImageIcon JIAddAccount,JIAddBook,JISearchStudent,JISearchBook,JISetting,JILogout;
    Dimension screenSize;
    public Home(Main frame){
        this.mFrame=frame;
        mCheckDatabase=new UpdateDatabase(mFrame);
        screenSize= Toolkit.getDefaultToolkit().getScreenSize(); 
        screenWidth = screenSize.width; 
        screenHeight = screenSize.height; 
        setSize(screenWidth,screenHeight);
        JDesktopPane theDesktop=new JDesktopPane();
        JMenuBar bar=new JMenuBar();
        JMenu filemenu=new JMenu("menu");
        JMenu Home=new JMenu("Home");
              JMenuItem about=new JMenuItem("About");
              JMenuItem exit =new JMenuItem("Home");
                  about.setMnemonic('A');
                  about.setMnemonic('H');
                  filemenu.add(about);
                  filemenu.add(Home);
               bar.add(filemenu);
               bar.add(Home);
               bar.setLocation(0,10);
               bar.setBounds(0,0,screenWidth,30);
            add(bar);
        add(theDesktop);
    setLayout(null);  
    
    
    JIAddAccount=new ImageIcon(getClass().getResource("/img/add_account.png"));
    JIAddBook=new ImageIcon(getClass().getResource("/img/add.png"));
    
    
    jb_image1=new JButton(JIAddAccount);
    jb_image1.setBounds(screenSize.width/2-240,320,72,72);
    add(jb_image1);
    j_image1=new JLabel("Add Student");
    j_image1.setBounds(screenSize.width/2-240,392,100,30);
    setFontJLabel(j_image1);
    add(j_image1);
    //adding next 
    b_image2=new JButton(JIAddBook);
    b_image2.setBounds(screenSize.width/2-120,320,72,72);
    add(b_image2);
    
    
    
    //adding for searchStudent 
    JISearchStudent=new ImageIcon(getClass().getResource("/img/search.png"));
    JISearchBook=new ImageIcon(getClass().getResource("/img/rolleyes.png"));
    JISetting=new ImageIcon(getClass().getResource("/img/simple-smile.png"));
    
    /* The JPanels are */
    j_image2=new JLabel("Register Book");
    JLSearchBook=new JLabel("Search Book");
    
    j_image2.setBounds(screenSize.width/2-115,390,100,30);
    JLSearchBook.setBounds(screenSize.width/2+115,390,100,30);
    
    setFontJLabel(j_image2);
    setFontJLabel(JLSearchBook);
    
    add(j_image2);
    add(JLSearchBook);
    
    JBSearchStudent=new JButton(JISearchStudent);
    JBSearchBook=new JButton(JISearchBook);
    
    /* Setting the bouds */
    
    JBSearchStudent.setBounds(screenSize.width/2,320,JISearchStudent.getIconWidth(),JISearchStudent.getIconHeight());
    JBSearchBook.setBounds(screenSize.width/2+120,320,JISearchBook.getIconWidth(),JISearchStudent.getIconHeight());
    
    
    JLSearchStudent=new JLabel("Students Details");
    JLSearchStudent.setBounds(screenSize.width/2-5,392,100,30);
    setFontJLabel(JLSearchStudent);
    
    
    //Adding all the componenets to this JPanel
    add(JBSearchStudent);
    add(JBSearchBook);
    
    add(JLSearchStudent);
    
    //Adding the ActionListener
    EventHandellerHome mEventhandellerHome=new EventHandellerHome();
    jb_image1.addActionListener(mEventhandellerHome);
    b_image2.addActionListener(mEventhandellerHome);
    JBSearchStudent.addActionListener(mEventhandellerHome);
    JBSearchBook.addActionListener(mEventhandellerHome);
    
    ExecutorService mThreadExcutor=Executors.newCachedThreadPool();
    mThreadExcutor.execute(mCheckDatabase);
    }
    public void setFontJLabel(JLabel mLabel){
    Font font = new Font("Courier", Font.BOLD,12);
    mLabel.setForeground(Color.WHITE);
    mLabel.setFont(font);
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
        ImageIcon logoIcon=new ImageIcon(getClass().getResource("/img/tu-logo.png"));
        mBackroundmg=logoIcon.getImage();
        g.drawImage(mBackroundmg,screenSize.width/2-300,100,null); 
        g.drawImage(mBackroundmg,screenSize.width/2+150,100,null);
        g.drawLine(screenSize.width/2-250,250,screenSize.width/2+200,250);
    }
    private class EventHandellerHome implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==jb_image1){
                     mFrame.remove(mFrame.mHome);
                     mFrame.add(mFrame.mRegisterStudent);
                     mFrame.revalidate();
                     mFrame.repaint();
             }else if(e.getSource()==b_image2){
                     mFrame.remove(mFrame.mHome);
                     mFrame.add(mFrame.mRegisterBook);
                     mFrame.revalidate();
                     mFrame.repaint();
             }else if(e.getSource()==JBSearchStudent){
                     mFrame.remove(mFrame.mHome);
                     mFrame.add(mFrame.mSearchStudent);
                     mFrame.revalidate();
                     mFrame.repaint();
             }else if(e.getSource()==JBSearchBook){
                     mFrame.remove(mFrame.mHome);
                     mFrame.add(mFrame.mSearchBook);
                     mFrame.revalidate();
                     mFrame.repaint();
             }
        }
    }
}
