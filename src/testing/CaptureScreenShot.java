package testing;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CaptureScreenShot {
    BufferedImage mBufferedImage;
    public CaptureScreenShot(String mFileName){
        try{
        mBufferedImage=new Robot().createScreenCapture(
                new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        }catch(AWTException e){
            
        }
        File mFile=new File(mFileName+".jpg");
        
        try{
        ImageIO.write(mBufferedImage,"jpg",mFile);
        }catch(IOException e){
            
        }
    }
    public static void main(String []args){
        //CaptureScreenShot mCaptureScreenShot=new CaptureScreenShot();
    }
}
