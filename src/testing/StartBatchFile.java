package testing;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartBatchFile {
    String location;
    StartBatchFile(){
        System.out.println(getClass().getResource("/mysql/bin/mysqld.exe").toString());
        try{
         Process proces=Runtime.getRuntime().exec(getClass().getResource("/mysql_stop.bat").toString());
        }catch (IOException ex) {
            
        }
    }
    public static void main(String[]args){
        StartBatchFile m=new StartBatchFile();
    }
}
