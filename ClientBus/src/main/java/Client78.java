

//File name Client78.java
//Eiar 5770  update Sivan  5778
//Levian Yehonatan
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

class Client78
{

    String SERVERHOST = "LOCALHOST";
//    String SERVERHOST = "";
//    String SERVERHOST = "147.161.23.20";

    int DEFAULT_PORT = 50002;
    Socket clientSocket = null;
    BufferedReader bufferSocketIn;
    PrintWriter bufferSocketOut;
    BufferedReader keyBoard;
    ClientWin78 myOutput;
    String line;
    ArrayList<String> stationList;
    

    public Client78() {
		super();
		stationList=new  ArrayList<String>();
	}

	public void doit()
    {
        try
        {
            // request to server
            clientSocket = new Socket(SERVERHOST, DEFAULT_PORT);

            // Init streams to read/write text in this socket
            bufferSocketIn = new BufferedReader(
                    new InputStreamReader(
                    clientSocket.getInputStream()));
            bufferSocketOut = new PrintWriter(
                    new BufferedWriter(
                    new OutputStreamWriter(
                    clientSocket.getOutputStream())), true);


//  	   Init streams to read text from the keyboard
//	   keyBoard = new BufferedReader(
//	   new InputStreamReader(System.in));


            myOutput = new ClientWin78("BUS  ", this);

            
           
            
            
          String busLine=myOutput.sendBusLine();
        
          line = bufferSocketIn.readLine();
          int size=Integer.parseInt(line);
          
          for(int i=0;i<size;i++) {
        	  line = bufferSocketIn.readLine();
        	  stationList.add(line);
          }  
            
         
          for (String station : stationList) 
            {	myOutput.clear();
        	  	myOutput.print("bus number:" +busLine);
        	  	myOutput.print("in station "+station);
                bufferSocketOut.println(station); // reads a line from the server
                try {Thread.sleep(10000);} catch (InterruptedException e) {}
               
            }
          bufferSocketOut.println("end");
          myOutput.print("finish please get off buss");
          
        } catch (IOException e)
        {
            myOutput.printMe(e.toString());
            System.err.println(e);
        } finally
        {
            try
            {
                if (clientSocket != null)
                {
                    clientSocket.close();
                }
            } catch (IOException e2)
            {
            }
        }
        
        

        System.out.println("end of client ");
    }

	

    public static void main(String[] args)
    {
        Client78 client = new Client78();
        
        client.doit();
    }
}
