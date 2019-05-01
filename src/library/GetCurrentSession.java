/*
 * What i think about current session is it is quite delay to handle the
 * all interrupt.
 * This class return the current Session of student who is checked in.
 */
package library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GetCurrentSession {
    String mCurrentSessionID;
    public GetCurrentSession(){
        File mFile=new File("D:\\linux\\ArdinoProject\\CurrentSession.dat"); 
        try{
            Scanner input=new Scanner(mFile);
            mCurrentSessionID=input.nextLine();
        }catch(FileNotFoundException e){
            System.out.println("not Exists");
        } 
    }
    public String getCurrentSession(){
        return mCurrentSessionID;
    }
}
