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

import org.newdawn.slick.state.GameState;

import Core.ClassesInstances;
import States.ServerGameState;

public class SerwerThread extends Thread {
	Socket mySocket;
	private int liczba_punktow=0;
	private int liczba_pytan=0;
	private String identifier;
	private ServerGameState serverGameState;
	
	public SerwerThread(Socket clientSocket) {
		// TODO Auto-generated constructor stub
		super(); 
		mySocket = clientSocket;
		serverGameState=(ServerGameState) ClassesInstances.serverGameState;
	}

	public void run()
	{
		try {

			PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));

			out.println("##SERWER MESSAGE## Nawiązano połaczenie z klientem"); // pierwsze wysłanie
			while(true)
				{
					if(in.ready())
					{
						System.out.println(in.readLine());//pierwsze przyjęcie
						break;
					}
				}
			while(!Thread.currentThread().isInterrupted())
			{
				out.println(String.valueOf(serverGameState.get_player_1_x_position()));
				out.println(String.valueOf(serverGameState.get_player_1_y_position()));
				
				while(!Thread.currentThread().isInterrupted())
				{
					if(in.ready())
					{
						serverGameState.set_player_2_x_position(Integer.valueOf(in.readLine()));
						break;
					}
				}
				while(!Thread.currentThread().isInterrupted())
				{
					if(in.ready())
					{
						serverGameState.set_player_2_y_position(Integer.valueOf(in.readLine()));
						break;
					}
				}
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


