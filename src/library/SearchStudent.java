package library;


import database.EllewRetriveBookDetailFromDatabase;
import database.EllewRetriveStudentDetailFromDatabase;
import database.EllewSearchStudent;
import database.EllewUpdateBook;
import database.EllewUpdateStudent;
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

public class SearchStudent extends JPanel{
    Main mFrame;
    Dimension screenSize;
    JTextArea JTarea;
    EllewSearchStudent mEllewSearchStudent;
    JList JLOption;
    ArrayList <String> ArrayOption;
    ImageIcon imgLocation;
    JDesktopPane theDesktop;
    ImageIcon imgicon;
    JLabel iconLabel;
    JTextField JTStudentName,JTStudentID,JTStudentAddress,JTStudentRFID;
    JTextField JTSn,JTBookID,JTBookName,JTBookView;
    JButton JBEdit,JBUpdate;
    public SearchStudent(Main mMain){
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
    JTarea= new JTextArea("Enter Student ID :");
    JTarea.setForeground(Color.WHITE);
    JTarea.setFont(new Font("New Times Roman",Font.BOLD,30));
    JTarea.setOpaque(false);
    JTarea.setBounds(screenSize.width/2-250,220,500,50);
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
                 if("Enter Student ID :".equals(searchData)){
                     JTarea.setText("");
                 }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                mEllewSearchStudent=null;
                String searchData=JTarea.getText();
                SearchStudent.this.ArrayOption.clear();
                mEllewSearchStudent=new EllewSearchStudent();
                ArrayOption =mEllewSearchStudent.getList(searchData);
                JLOption=new JList(ArrayOption.toArray());
                JLOption.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
                JLOption.setBounds(screenSize.width/2-250,320,450,ArrayOption.size()*30);
                JLOption.setOpaque(false);   
                
                SearchStudent.this.removeAll();
                loadDesktop();    
                SearchStudent.this.add(JTarea);
                JTarea.grabFocus();
                SearchStudent.this.add(JLOption);
                SearchStudent.this.revalidate();
                SearchStudent.this.repaint();
                //JOptionPane.showInputDialog(this,searchData); 
                 if(searchData.isEmpty()){
                     JTarea.setText("Enter Student ID :"); 
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
                        String mStudentID=ArrayOption.get(JLOption.getSelectedIndex());
                        JTarea.setText(ArrayOption.get(JLOption.getSelectedIndex()));
                        EllewRetriveStudentDetailFromDatabase mS=new EllewRetriveStudentDetailFromDatabase();
                        mS.searchUsingStudentID(ArrayOption.get(JLOption.getSelectedIndex()));
                        removeDisplay(JLOption);
                        
                        String mImageLocation=mS.getStudentImageLocation();
                        String mStudentName    =mS.getStudentName();
                        String mStudentAddress   =mS.getStudentAddress();
                        String mStudentRFID  =mS.getStudentRFID();
                        ArrayList <String> mBookID=mS.getIssuedBookList(mStudentID);
                        display(mBookID,mImageLocation,mStudentName,mStudentID,mStudentAddress,mStudentRFID);
                        break;  
                    case KeyEvent.VK_BACK_SPACE:
                        if(iconLabel!=null){
                            removeDisplay(iconLabel);
                            removeDisplay(JTStudentName);
                            removeDisplay(JTStudentID);
                            removeDisplay(JTStudentAddress);
                            removeDisplay(JTStudentRFID);
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
                   /* Adding action to Register Book */
                   MTRegisterBook.setEnabled(false);
                   
                   /* Adding action to the Register Student */
                   MTRegisterStudent.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent e){
                           mFrame.remove(mFrame.mSearchStudent);
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
                        mFrame.remove(mFrame.mSearchStudent);
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
    private void display(ArrayList<String> mBookID,String imgLocation,String mStudentName,String mStudentID,String mStudentAddress,String mStudentRFID){
        imgicon=new ImageIcon(imgLocation);
        iconLabel=new JLabel(imgicon);
        JTStudentName=new JTextField(mStudentName);
        JTStudentID  =new JTextField(mStudentID);
        JTStudentAddress=new JTextField(mStudentAddress);
        JTStudentRFID=new JTextField(mStudentRFID);
        
        
        JBEdit=new JButton("Edit");
        JBUpdate=new JButton("Update");        
        
        JTSn=new JTextField("SN");
        JTBookID=new JTextField("        Book ID");
        JTBookName=new JTextField("                  Book Name");
        JTBookView=new JTextField("  view  ");
        
        /* Positioning the componenet into the panel */
        iconLabel.setBounds(screenSize.width/2-550,300,200,200);
        
        JTSn.setBounds(screenSize.width/2-330,300,50,30);
        JTBookID.setBounds(screenSize.width/2-280,300,150,30);
        JTBookName.setBounds(screenSize.width/2-130,300,340,30);
        JTBookView.setBounds(screenSize.width/2+210,300,70,30);
        
        /*
        * Here implement the book in database
        */
        
        for(int i=1;i<=mBookID.size();i++){
            String tempBookID=mBookID.get(i-1);
            EllewRetriveBookDetailFromDatabase mRBD=new EllewRetriveBookDetailFromDatabase();
            mRBD.searchUsingBookID(tempBookID);
           
            JTextField JTmSn=new JTextField(""+i);
            JTextField JTmBookID=new JTextField(tempBookID);
            JTextField JTmBookName=new JTextField(mRBD.getBookName());
            JButton mBookView=new JButton("view");
            ImageIcon mBookImage=new ImageIcon(mRBD.getBookCoverImagLocation());
            
            JTmSn.setBounds(screenSize.width/2-330,300+i*30, 50,30);
            JTmBookID.setBounds(screenSize.width/2-280,300+i*30,150,30);
            JTmBookName.setBounds(screenSize.width/2-130,300+i*30,340,30);
            mBookView.setBounds(screenSize.width/2+210,300+i*30,70,30);
            mBookView.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JOptionPane.showMessageDialog(mFrame,
                            "\nName      :   "+mRBD.getBookName() +
                            "\nBook ID   :   "+tempBookID+      
                            "\nAuthor    :   "+ mRBD.getBookAuthor(),
                            mRBD.getBookName(),JOptionPane.INFORMATION_MESSAGE,mBookImage);
                }
            });
            setFontJTextField(JTmSn);
            setFontJTextField(JTmBookID);
            setFontJTextField(JTmBookName);
  
            
            add(JTmSn);
            add(JTmBookID);
            add(JTmBookName);
            add(mBookView);
        }
        JTStudentName.setBounds(screenSize.width/2+300,300,230,30);
        JTStudentID.setBounds(screenSize.width/2+300,330,230,30);
        JTStudentAddress.setBounds(screenSize.width/2+300,360,230,30);
        JTStudentRFID.setBounds(screenSize.width/2+300,390,230,30);
        
        JBEdit.setBounds(screenSize.width/2+300,450,110,30);
        JBUpdate.setBounds(screenSize.width/2+420,450,110,30);
        
        
        /* Setting up the font and color for the component */
        setFontJTextField(JTStudentName);
        setFontJTextField(JTStudentID);
        setFontJTextField(JTStudentAddress);
        setFontJTextField(JTStudentRFID);
        
        setFontJTextField(JTSn);
        setFontJTextField(JTBookID);
        setFontJTextField(JTBookName);
        setFontJTextField(JTBookView);
        
        add(iconLabel);  
        
        add(JTSn);
        add(JTBookID);
        add(JTBookName);
        add(JTBookView);
        
        add(JTarea);
        add(JTStudentID);
        add(JTStudentAddress);
        add(JTStudentRFID);
        add(JTStudentName);
        add(JTStudentAddress);
        add(JTStudentRFID);
        
        add(JBUpdate);
        add(JBEdit);
        
        
        revalidate();
        repaint();
        JBEdit.addActionListener(new ActionListener(){    
            @Override
            public void actionPerformed(ActionEvent e) {
                JTStudentName.setEditable(true);
                JTStudentAddress.setEditable(true);
            }
        });
        JBUpdate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                EllewUpdateStudent mEllewUpdateStudent=new EllewUpdateStudent();
                if(mEllewUpdateStudent.StudentDetailsUpdate(mStudentID,JTStudentName.getText(),JTStudentAddress.getText())){
                    JOptionPane.showMessageDialog(mFrame,"Successfully Updated.","Database is updated.",JOptionPane.INFORMATION_MESSAGE);
                }
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
        g.drawImage(logoIcon.getImage(),screenSize.width/2-500,50,null); 
        g.drawImage(logoIcon.getImage(),screenSize.width/2+350,50,null);
        g.drawLine(screenSize.width/2-350,200,screenSize.width/2+300,200);
        g.drawLine(screenSize.width/2-350,280,screenSize.width/2+300,280);
       // g.drawString("Enter Student ID",screenSize.width/2-150,300);
   }
}
