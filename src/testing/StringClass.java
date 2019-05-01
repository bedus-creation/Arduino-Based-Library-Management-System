package testing;

import java.util.Scanner;

public class StringClass {
    public static void main(String [] args){
        Scanner cin=new Scanner(System.in);
        String [] name = new String[20];
        String nam=null;
        for(int i=0;i<5;i++){
            System.out.println("Enter name");
            try{
            name[i]=cin.next();
            }catch(Exception e){
                System.err.print(e);
            }
        }
        for(int i=0;i<5;i++){
            System.out.println(nam);
        }
    }    
}
