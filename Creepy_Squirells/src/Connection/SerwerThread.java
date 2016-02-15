package Connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class SerwerThread extends Thread {
	Socket mySocket;
	private int liczba_punktow=0;
	private int liczba_pytan=0;
	private String identifier;
	
	public SerwerThread(Socket clientSocket) {
		// TODO Auto-generated constructor stub
		super(); 
		mySocket = clientSocket;
	}

	public void run()
	{
		try {

			PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));

			out.println("##SERWER MESSAGE## \nNawiązano połaczenie z klientem"); // pierwsze wysłanie
			while(true)
				{
					if(in.ready())
					{
						identifier=in.readLine();//pierwsze przyjęcie
						break;
					}
				}
			while(!Thread.currentThread().isInterrupted())
			{
				
			}
			
			
			out.println("koniec");	
			in.close();
			out.close();
			mySocket.close();
		 
		}
		catch (Exception e) 
		{
			System.err.println(e);
		}
		
	}
}


