package testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class AudioPlay {
    String mFile;
    public AudioPlay(String choice){
        switch(choice){
            case "done":
                mFile=getClass().getResource("/Music/correct.wav").getFile();
                //mFile="C:\\Users\\hackers\\Documents\\NetBeansProjects\\Library\\build\\classes\\Music\\correct.wav";
                play();
                break;
            case "error":
                mFile=getClass().getResource("/Music/question.wav").getFile();
                play();
                break;
            default:
                break;
        }
    } 
    private void play(){
        AudioStream audioStream=null;
        try{
        InputStream in=new FileInputStream(mFile);
        audioStream=new AudioStream(in);
        }catch(FileNotFoundException e){
            
        }catch(IOException s){
            
        }
        AudioPlayer.player.start(audioStream);
    }
    /*
    public static void main(String []args) throws IOException{
        AudioPlay mPlay=new AudioPlay("error"); 
    }
    */
}
