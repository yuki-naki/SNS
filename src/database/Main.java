package database;

public class Main {
    public static void main(String[] args){
        BLOBInserter bi = new BLOBInserter();
        GroupBLOBInserter gi = new GroupBLOBInserter();
        bi.insertBlob("C:/workspace/SNS/WebContent/img/image.jpg");
        gi.insertBlob("C:/workspace/SNS/WebContent/img/image.jpg");
        System.out.println("OK");
    }
}
