package library;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class ShowBookError extends JFrame {
    Dimension screenSize;
    public ShowBookError(){
        screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        setSize(500,300);
        setVisible(true);
        setResizable(false);
    }
}
