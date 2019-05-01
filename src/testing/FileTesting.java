package testing;
/*
* This class simply find which user's Id card is inserted into the system.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileTesting {
     public static void main(String[]args){
        File mFile=new File("D:\\linux\\ArdinoProject\\CurrentSession.dat"); 
        try{
            Scanner input=new Scanner(mFile);
            String StudentCurrentSessionID=input.nextLine();
            System.out.println(StudentCurrentSessionID);
        }catch(FileNotFoundException e){
            System.out.println("not Exists");
        }  
    } 
}
