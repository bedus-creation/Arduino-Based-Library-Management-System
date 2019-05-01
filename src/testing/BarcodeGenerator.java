package testing;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
public class BarcodeGenerator extends JFrame{
    public BarcodeGenerator(){
        BarcodeGeneratorPanel panel=null;
        try {
            panel = new BarcodeGeneratorPanel(123456789,001,10);
        } catch (Exception ex) {
            Logger.getLogger(BarcodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        add(panel);
        revalidate();
        repaint();    
    }
    public static void main(String[]args){
        BarcodeGenerator apps=new BarcodeGenerator();
        apps.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        apps.setVisible(true);
        apps.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
