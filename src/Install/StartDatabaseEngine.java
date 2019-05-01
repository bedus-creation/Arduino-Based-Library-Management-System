/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Install;
public class StartDatabaseEngine {
    String location_database="service mysql start";
    //String location_database="C:\\xampp\\mysql\\bin\\mysqld.exe";
    public StartDatabaseEngine(){
        try{
            Process process=Runtime.getRuntime().exec(location_database);
            System.out.println("Mysql Database is started.");
        }catch(Exception e){
            /*
            * print the message to install mysql database in your system.
            */
            System.out.println("error"+e.getMessage());
            System.exit(1);
        }           
    }  
}
