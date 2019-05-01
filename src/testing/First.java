package Tutorial.multiThreading;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class First implements Runnable{
    String name;
    public First(String mTaskname){
        name=mTaskname;
        System.out.print(mTaskname);
    }
    @Override
    public void run() {
        try{
            System.out.print("This is "+name);
            Thread.sleep(3000);
        }catch (InterruptedException ex) {
           
        }
    }
    public static void main(String [] args){
        First mFrist=new First("This is First");
        First mSecond=new First("This is second");
        ExecutorService mThreadExcutor=Executors.newCachedThreadPool();
        mThreadExcutor.execute(mFrist);
        mThreadExcutor.execute(mSecond);
    }
}
