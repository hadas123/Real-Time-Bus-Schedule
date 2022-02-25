
//file name: StationUseServer78.java
//Iyar 5770  update Sivan 5778
//Levian Yehonatan

public class StationUseServer78 
{
    public static void main(String[] arg)
    {	MessageManager messagemanager= new MessageManager();
        new StationServer78(messagemanager);
        new BusServer78(messagemanager);
    }

}
