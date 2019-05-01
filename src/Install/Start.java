package Install;


public class Start {
    
    public Start(){
        new StartDatabaseEngine();
        new DatabaseConnectionTesting();
        new CreateDatabase();
        new CreateTable();
    }
    public static void main(String[] args){
        Start app=new Start();
    }
}
