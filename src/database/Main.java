package database;

public class Main {
    public static void main(String[] args){
        BLOBInserter bi = new BLOBInserter();
        bi.insertBlob("C:/workspace/SNS/WebContent/img/user_default.png");
        System.out.println("OK");
    }
}
