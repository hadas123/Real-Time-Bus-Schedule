

//file name: StationDialog78.java
//Iyar 5770 update Sivan 5778
//Levian Yehonatan
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class StationDialog78 extends Thread // parallel dialogs on the same socket
{

    Socket client;
    StationServer78 myServer;
    BufferedReader bufferSocketIn;
    PrintWriter bufferSocketOut;
    StationDialogWin78 myOutput;
    MessageManager messagemanager;
    Event64 event=new Event64();
    
    public StationDialog78(Socket clientSocket, StationServer78 myServer, MessageManager messagemanager)
    {	this.messagemanager=messagemanager;
        client = clientSocket;
        this.myServer = myServer;
        try
        {
            // Init streams to read/write text in this socket
            bufferSocketIn = new BufferedReader(
                    new InputStreamReader(
                    clientSocket.getInputStream()));
            bufferSocketOut = new PrintWriter(
                    new BufferedWriter(
                    new OutputStreamWriter(
                    clientSocket.getOutputStream())), true);
        } catch (IOException e)
        {
            try
            {
                client.close();
            } catch (IOException e2)
            {
            }
            System.err.println("server:Exception when opening sockets: " + e);
            return;
        }
        myOutput = new StationDialogWin78("Dialog Win for: " + client.toString(), this);
        start();
    }

    public void run()
    {
        
        
        try
        {	
        	//initilize
        	String StationName=bufferSocketIn.readLine();
        	myOutput.print("i am dialog of Sataion"+StationName);
        	this.messagemanager.AddStation(StationName, event);
        	
        	
        	 while (true)
            {	
        		String message=(String)event.waitEvent();
                bufferSocketOut.println(message);
                myOutput.printMe(message);
            }
        	
        } catch (IOException e)
        {
        	 //delete frome messagemanager-how to delete there isnt function
        } finally
        {
            try
            {
                client.close();
            } catch (IOException e2)
            {
            }
        }

        myOutput.printMe("end of  dialog ");
        myOutput.send.setText("Close");

    }

    void exit()
    {
            try
            {
                client.close();
            } catch (IOException e2)
            {
            }
    }
}
