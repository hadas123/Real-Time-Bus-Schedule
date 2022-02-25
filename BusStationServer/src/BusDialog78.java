

//file name: BusDialog78.java
//Iyar 5770 update Sivan 5778
//Levian Yehonatan
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

class BusDialog78 extends Thread // parallel dialogs on the same socket
{

    Socket client;
    BusServer78 myServer;
    BufferedReader bufferSocketIn;
    PrintWriter bufferSocketOut;
    BusDialogWin78 myOutput;
    MessageManager messagemanager;
    public BusDialog78(Socket clientSocket, BusServer78 myServer, MessageManager messagemanager)
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
        myOutput = new BusDialogWin78("Dialog Win for:Station", this);
        start();
    }

    public void run()
    {
        String line;
        String bussLine;
        try
        {
        	
        	bussLine=bufferSocketIn.readLine();
        	myOutput.print(bussLine);
        	ArrayList<String> sti=messagemanager.getStations(bussLine);
        	bufferSocketOut.println(sti.size());
        	for (String string : sti) 
        	{	
        		bufferSocketOut.println(string);
        		
        	}
        
        	
        	
            while (true)
            {	
                line = bufferSocketIn.readLine();
                
                if (line == null)
                    break;
                if (line.equals("end"))
                    break;
                
                messagemanager.announceApproach(bussLine, line);
               
                myOutput.printOther(line);
            }
        } catch (IOException e)
        { 
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
