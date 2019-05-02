package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GetCurrentStudentID {
    String mCurrentSessionID;
    File mFile;
    public GetCurrentStudentID(){
        mCurrentSessionID=null;
        mFile=new File("C:\\wamp\\www\\ArdinoProject\\CurrentSession.dat");
        try{
            Scanner input=new Scanner(mFile);
            mCurrentSessionID=input.nextLine();
        }catch(FileNotFoundException e){
            System.out.println("Fatel error");
        }
        
    }
    public String getSessionID(){
        return mCurrentSessionID;
    }
}
