package testing;

import com.onbarcode.barcode.Code93;
import com.onbarcode.barcode.IBarcode;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BarcodeGeneratorPanel extends JPanel {
    Image [] mImage=new Image[50]; 
    int number;
    JButton bSaveImage;
    public BarcodeGeneratorPanel(int netID,int hostID,int num) throws Exception{
        this.number=num;
        bSaveImage=new JButton("Save Image");
        bSaveImage.setBounds(600,500,100,30);
        add(bSaveImage);
        for(int i=1;i<=num;i++){
        String temp1=String.format("%09d",netID);
        hostID=hostID+1;
        String temp2=String.format("%03d",hostID);
        System.out.print(temp1+temp2);
        mImage[i]=testCODE93(temp1+temp2);
       }
      BarcodeGeneratorActnionListener mBarcodeGeneratorActnionListener=new BarcodeGeneratorActnionListener();
      bSaveImage.addActionListener(mBarcodeGeneratorActnionListener);
    }
    private Image testCODE93(String number) throws Exception
       {
        Code93 barcode = new Code93();

        /*
           Code 93 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
                A - Z (Uppercase letters)
                - (Dash), $ (Dollar), % (Percentage), (Space), . (Point), / (Slash), + (Plus)
        */
        barcode.setData(number);

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(5f);
        // barcode bar module height (Y) in pixel
        barcode.setY(300f);

        // barcode image margins
        barcode.setLeftMargin(0f);
        barcode.setRightMargin(0f);
        barcode.setTopMargin(10f);
        barcode.setBottomMargin(10f);

        // barcode image resolution in dpi
        barcode.setResolution(3000);

        // disply barcode encoding data below the barcode
        //barcode.setShowText(true);
        // barcode encoding data font style
        //barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
        // space between barcode and barcode encoding data
        //barcode.setTextMargin(6);
        //barcode.setTextColor(AndroidColor.black);
        
        // barcode bar color and background color in Android device
        barcode.setForeColor(Color.BLACK);
        barcode.setBackColor(Color.white);

        /*
        specify your barcode drawing area
	    */
	    //RectF bounds = new RectF(0, 0, 0, 0);
        barcode.setBarcodeWidth(200);
        barcode.setBarcodeHeight(50);
         return barcode.drawBarcode();
        /*File iBackground=new File("src\\img\\image-5.jpg");
        ImageIcon iconBackground=new ImageIcon(iBackground.getAbsolutePath());
        im=iconBackground.getImage();
        */        
    }
        @Override
    protected void paintComponent(Graphics g){
        int k=1;
        for(int i=0;i<number/2;i++){
            for(int j=1;j<=2;j++){
                g.drawImage(mImage[k],100+(j%2)*250,20+120*i,200,120,null);
                k++;
            }
        }
    }
    private class BarcodeGeneratorActnionListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==bSaveImage){
                    System.out.print("hellow brother ");
                    CaptureScreenShot mCaptureScreenShot=new CaptureScreenShot("screenWidth");
                }
            }   
        }
}
