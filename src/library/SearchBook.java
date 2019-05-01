package library;

import database.EllewRetriveBookDetailFromDatabase;
import database.EllewSearchBook;
import database.EllewUpdateBook;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SearchBook extends JPanel{
    Main mFrame;
    Dimension screenSize;
    JTextArea JTarea;
    EllewSearchBook mEllewSearchBook;
    JList JLOption;
    ArrayList <String> ArrayOption;
    ImageIcon imgLocation;
    JDesktopPane theDesktop;
    ImageIcon imgicon;
    JLabel iconLabel;
    JTextField JTBookName,JTBookID,JTBookAuthor,JTBookLocation,JTBookBorrower;
    JButton JBEdit,JBUpdate;
    public SearchBook(Main mMain){
        this.mFrame=mMain;
        screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        /* Starting up the array */
        ArrayOption=new ArrayList<String> ();    
        imgLocation=null;
    setLayout(null);  
    loadDesktop();
    /*
    *  Adding the content 
    */
    JTarea= new JTextArea("Enter Book ID :");
    JTarea.setForeground(Color.WHITE);
    JTarea.setFont(new Font("New Times Roman",Font.BOLD,30));
    JTarea.setOpaque(false);
    JTarea.setBounds(screenSize.width/2-250,270,450,50);
    JTarea.grabFocus();
    /* Addind the component into the panel; */
    add(JTarea);
    
    JTarea.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
               String searchData=JTarea.getText();
                //JOptionPane.showInputDialog(this,searchData); 
                 if("Enter Book ID :".equals(searchData)){
                     JTarea.setText("");
                 }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                mEllewSearchBook=null;
                String searchData=JTarea.getText();
                SearchBook.this.ArrayOption.clear();
                mEllewSearchBook=new EllewSearchBook();
                ArrayOption =mEllewSearchBook.getList(searchData);
                JLOption=new JList(ArrayOption.toArray());
                JLOption.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
                if(ArrayOption.size()<=5){
                    JLOption.setBounds(screenSize.width/2-250,320,450,ArrayOption.size()*30);
                }else{
                    JLOption.setBounds(screenSize.width/2-250,320,450,5*30);
                    JLOption.setAutoscrolls(true);
                    JLOption.setVisibleRowCount(5);
                }
                JLOption.setOpaque(false);   
                
                SearchBook.this.removeAll();
                loadDesktop();    
                SearchBook.this.add(JTarea);
                JTarea.grabFocus();
                SearchBook.this.add(JLOption);
                SearchBook.this.revalidate();
                SearchBook.this.repaint();
                //JOptionPane.showInputDialog(this,searchData); 
                 if(searchData.isEmpty()){
                     JTarea.setText("Enter Book ID :"); 
                 }else{
                     /* Selectiong the first row if nothing is selected */
                     if(JLOption.isSelectionEmpty()){
                        JLOption.setSelectedIndex(0);
                     }
                     switch(e.getKeyCode()){
                    case KeyEvent.VK_DOWN:
                        /* Testing whether the value is out of range */
                        if(JLOption.getSelectedIndex()+1<=ArrayOption.size()){
                         JLOption.setSelectedIndex(JLOption.getSelectedIndex()+1);
                        }
                        break;
                    case KeyEvent.VK_UP:
                        System.out.println("Enter the tex");
                        break;
                    case KeyEvent.VK_RIGHT:
                        /* Setting the text when the right button is p[ressed */
                        String mBookID=ArrayOption.get(JLOption.getSelectedIndex());
                        JTarea.setText(ArrayOption.get(JLOption.getSelectedIndex()));
                        EllewRetriveBookDetailFromDatabase mS=new EllewRetriveBookDetailFromDatabase();
                        mS.searchUsingBookID(ArrayOption.get(JLOption.getSelectedIndex()));
                        removeDisplay(JLOption);
                        
                        String mImageLocation=mS.getBookCoverImagLocation();
                        String mBookName    =mS.getBookName();
                        String mBookAuthor   =mS.getBookAuthor();
                        String mBookLocation  =mS.getBookSection();
                        String mBookBorrower =mS.getBookBorrower();
                        System.out.println(mImageLocation+mBookAuthor);
                        display(mImageLocation,mBookName,mBookID,mBookAuthor,mBookLocation,mBookBorrower);
                        
                        break;  
                    case KeyEvent.VK_BACK_SPACE:
                        if(iconLabel!=null){
                            removeDisplay(iconLabel);
                            removeDisplay(JTBookName);
                            removeDisplay(JTBookID);
                            removeDisplay(JTBookAuthor);
                            removeDisplay(JTBookLocation);
                            removeDisplay(JTBookBorrower);
                        }
                        break;
                    default:
                        break;
                    }
                 }
            }
    });
    }
    private void loadDesktop(){
    /*
    *   setting the menus 
    */
    theDesktop=new JDesktopPane();
        JMenuBar bar=new JMenuBar();
        JMenu JMmenu=new JMenu("Menu");
        JMenu JMAbout=new JMenu("About");
            JMenuItem MTHome=new JMenuItem("Home");  /* MeniItem of Menu */
            JMenuItem MTExit =new JMenuItem("Exit"); /* MenuItem of Menu */
            JMenuItem MTRegisterStudent=new JMenuItem("Register Student");
            JMenuItem MTRegisterBook=new JMenuItem("Register Book");
            JMenuItem MTSearchStudent=new JMenuItem("Search Student");
            JMenuItem MTSearchBook =new JMenuItem("Search Book");
                  MTHome.setMnemonic('H');
                  MTExit.setMnemonic('E');
                    /*
                    *   Adding Action to the Exit
                    */
                   MTExit.addActionListener(
                        new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                System.exit(1);
                            }  
                        });
                   /* Adding action to Searchbook */
                   MTSearchBook.setEnabled(false);
                   
                   /* Adding action to the Register Student */
                   MTRegisterStudent.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent e){
                           mFrame.remove(mFrame.mSearchBook);
                           mFrame.add(mFrame.mRegisterStudent);
                           mFrame.revalidate();
                           mFrame.repaint();
                       }
                   });
                   /*
                   *  Adding action to the Home 
                   */
                   MTHome.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent e){
                        mFrame.remove(mFrame.mSearchBook);
                        mFrame.add(mFrame.mHome);
                        mFrame.revalidate();
                        mFrame.repaint();
                       }
                   });
                  JMmenu.add(MTHome);
                  JMmenu.add(MTExit);
                  JMmenu.add(MTSearchStudent);
                  JMmenu.add(MTSearchBook);
                  JMmenu.add(MTRegisterStudent);
                  JMmenu.add(MTRegisterBook);
               bar.add(JMmenu);
               bar.add(JMAbout);
               bar.setLocation(0,10);
               bar.setBounds(0,0,screenSize.width,30);
            add(bar);
        add(theDesktop);
        revalidate();
        repaint();
    }
    /* function clears the display*/
    private void removeDisplay(Component mComponent){
        remove(mComponent);
        revalidate();
        repaint();
    }
    /*
    * This function prints the picture and details of a student 
    */
    private void display(String imgLocation,String mBookName,String mBookID,
        String mBookAuthor,String mBookLocation,String mBookBorrower){
        imgicon=new ImageIcon(imgLocation);
        iconLabel=new JLabel(imgicon);
        JTBookName=new JTextField(mBookName);
        JTBookID  =new JTextField(mBookID);
        JTBookAuthor=new JTextField(mBookAuthor);
        JTBookLocation=new JTextField(mBookLocation);
        JTBookBorrower=new JTextField(mBookBorrower);
        
        JBEdit=new JButton("Edit");
        JBUpdate=new JButton("Update");
        
        /* Positioning the componenet into the panel */
        iconLabel.setBounds(screenSize.width/2-250,350,200,200);
        JTBookName.setBounds(screenSize.width/2-20,350,230,30);
        JTBookID.setBounds(screenSize.width/2-20,380,230,30);
        JTBookAuthor.setBounds(screenSize.width/2-20,410,230,30);
        JTBookLocation.setBounds(screenSize.width/2-20,440,230,30);
        JTBookBorrower.setBounds(screenSize.width/2-20,470,230,30);
        
        JBEdit.setBounds(screenSize.width/2-20,500,110,30);
        JBUpdate.setBounds(screenSize.width/2+100,500,110,30);
        
        /* Setting up the font and color for the component */
        setFontJTextField(JTBookName);
        setFontJTextField(JTBookID);
        setFontJTextField(JTBookAuthor);
        setFontJTextField(JTBookLocation);
        setFontJTextField(JTBookBorrower);

        
        
        add(iconLabel);   
        add(JTarea);
        add(JTBookID);
        add(JTBookAuthor);
        add(JTBookLocation);
        add(JTBookName);
        add(JTBookAuthor);
        add(JTBookLocation);
        add(JTBookBorrower);
        add(JBUpdate);
        add(JBEdit);
        
        revalidate();
        repaint();
        
        JBEdit.addActionListener(new ActionListener(){    
            @Override
            public void actionPerformed(ActionEvent e) {
                JTBookName.setEditable(true);
                JTBookAuthor.setEditable(true);
                JTBookLocation.setEditable(true);
            }
        });
        JBUpdate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String netID=JTBookID.getText().substring(0,9);
                EllewUpdateBook mEllewUpdateBook=new EllewUpdateBook();
                mEllewUpdateBook.BookDetailsUpdate(netID,JTBookName.getText(),JTBookAuthor.getText(),JTBookLocation.getText());
            }
        });
    }
    private void setFontJTextField(JTextField p){
        p.setEditable(false);
        //p.setOpaque(false);
        p.setForeground(Color.BLUE);
        p.setFont(new Font(Font.SERIF,Font.BOLD,20));
    }
    public void paintComponent(Graphics g) {
        ImageIcon iconBackground=new ImageIcon(getClass().getResource("/img/image-5.jpg"));
        g.drawImage(iconBackground.getImage(), 0, 0, null);     
        
        g.setColor(Color.red);
        g.setFont(new Font("New Times Roman", Font.BOLD,12));
        g.drawString("\"Technical education for economic growth\"",screenSize.width/2-150,50);
        g.setColor(Color.GREEN);
        g.setFont(new Font("New Times Roman", Font.BOLD,40));
        g.drawString("Purwanchal Engineering Campus",screenSize.width/2-350,100);
        g.setFont(new Font("New Times Roman", Font.PLAIN,30));
        g.drawString("Dharan-8 , Tinkune ",screenSize.width/2-150,170);
        ImageIcon logoIcon=new ImageIcon(getClass().getResource("/img/tu-logo.png"));
        g.drawImage(logoIcon.getImage(),screenSize.width/2-300,100,null); 
        g.drawImage(logoIcon.getImage(),screenSize.width/2+150,100,null);
        g.drawLine(screenSize.width/2-250,250,screenSize.width/2+200,250);
        g.drawLine(screenSize.width/2-250,330,screenSize.width/2+200,330);
       // g.drawString("Enter Book ID",screenSize.width/2-150,300);
   }
}
