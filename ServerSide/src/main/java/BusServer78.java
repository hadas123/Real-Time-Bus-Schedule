

//file name: BusServer78.java
//Iyar 5770  update Sivan 5778
//Levian Yehonatan
import java.io.*;
import java.net.*;

class BusServer78 extends Thread 	   //the parallel server
{

    int DEFAULT_PORT = 50002;
    ServerSocket listenSocket;
    Socket clientSockets;
    MessageManager messagemanager;
    public BusServer78(MessageManager messagemanager)   // constructor of a TCP server
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
        {	int id=0;
            while (true)
            {	
                clientSockets = listenSocket.accept();
                new BusDialog78(clientSockets, this,this.messagemanager,id);
                id++;
            }

        } catch (IOException e)
        {
            System.out.println("Problem listening server-socket");
            System.exit(1);
        }

        System.out.println("end of server");
    }
}

