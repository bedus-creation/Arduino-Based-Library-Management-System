package library.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import testing.AudioPlay;

public class BookNotIssuedErrorPanel extends JPanel{
    AudioPlay mPlay;
    ImageIcon iconBackground,iconTULogo;                
    public BookNotIssuedErrorPanel(String netID,String hostID){
        mPlay=new AudioPlay("error");
    }
    protected void paintComponent(Graphics g){
        iconBackground=new ImageIcon(getClass().getResource("/img/image-5.jpg"));
        iconTULogo=new ImageIcon(getClass().getResource("/img/tu-logo.png"));
        g.drawImage(iconBackground.getImage(),0,0,600,700,null);
        g.drawImage(iconTULogo.getImage(),20,30,null);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Courier", Font.BOLD,40));
        g.drawString("! Book Not Registered ",150,100);
    }
}
