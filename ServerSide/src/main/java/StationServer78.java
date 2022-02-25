

//file name: StationServer78.java
//Iyar 5770  update Sivan 5778
//Levian Yehonatan
import java.io.*;
import java.net.*;

class StationServer78 extends Thread 	   //the parallel server
{

    int DEFAULT_PORT = 50000;
    ServerSocket listenSocket;
    Socket clientSockets;
    MessageManager messagemanager;
    public StationServer78(MessageManager messagemanager)   // constructor of a TCP server
    {	this.messagemanager=messagemanager;
        try
        {
            listenSocket = new ServerSocket(DEFAULT_PORT);
        } catch (IOException e)    //error
        {
            System.out.println("Problem creating the server-socket");
            System.out.println(e.getMessage());
            System.exit(1);
        }

        System.out.println("Server starts on port " + DEFAULT_PORT);
        start();
    }

    public void run()
    {
        try
        {
            while (true)
            {
                clientSockets = listenSocket.accept();
                new StationDialog78(clientSockets, this,messagemanager);
            }

        } catch (IOException e)
        {
            System.out.println("Problem listening server-socket");
            System.exit(1);
        }

        System.out.println("end of server");
    }
}

