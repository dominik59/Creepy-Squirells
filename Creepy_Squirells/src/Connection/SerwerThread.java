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
				out.println(String.valueOf(serverGameState.get_first_player_picture())); //rysunek gracza pierwszego
				
				out.println(String.valueOf(serverGameState.get_posx())); //pozycja gracza *32 i albo + albo -1 do stworzenia wektora
				out.println(String.valueOf(serverGameState.get_posy())); //pozycja gracza *32 i albo + albo -1 do stworzenia wektora
				out.println(String.valueOf(serverGameState.get_pos_player_x())); //to samo co wyżej tylko jest dodane albo odjęte 32
				out.println(String.valueOf(serverGameState.get_pos_player_y())); //to samo co wyżej tylko jest dodane albo odjęte 32
				
				out.println(String.valueOf(serverGameState.get_lives_second())); //przekazanie ilości żyć gracza 2
				
				out.println(String.valueOf(serverGameState.get_player_1_fire_status())); // przekazanie statusu strzału gracza pierwszego
				
				while (!Thread.currentThread().isInterrupted()) {
					if (in.ready()) {
						serverGameState.set_player_2_x_position(Integer.valueOf(in.readLine())); //odczyt pozycji x gracza drugiego
						break;
					}
				}
				while (!Thread.currentThread().isInterrupted()) {
					if (in.ready()) {
						serverGameState.set_player_2_y_position(Integer.valueOf(in.readLine())); //odczyt pozycji y gracza drugiego
						break;
					}
				}
				while (!Thread.currentThread().isInterrupted()) {
					if (in.ready()) {
						serverGameState.set_second_player_picture(in.readLine()); //ustawienie rysunku gracza drugiego
						break;
					}
				}
				
				while (!Thread.currentThread().isInterrupted()) {
					if (in.ready()) {
						serverGameState.set_second_posx(Integer.valueOf(in.readLine())); //ustawienie second posx
						break;
					}
				}
				while (!Thread.currentThread().isInterrupted()) {
					if (in.ready()) {
						serverGameState.set_second_posy(Integer.valueOf(in.readLine())); //ustawienie second posy 
						break;
					}
				}
				
				while (!Thread.currentThread().isInterrupted()) {
					if (in.ready()) {
						serverGameState.set_second_pos_player_x(Integer.valueOf(in.readLine())); //ustawienie second pos player x
						break;
					}
				}
				
				while (!Thread.currentThread().isInterrupted()) {
					if (in.ready()) {
						serverGameState.set_second_pos_player_y(Integer.valueOf(in.readLine())); //ustawienie second pos player y
						break;
					}
				}
				
				while (!Thread.currentThread().isInterrupted()) {
					if (in.ready()) {
						serverGameState.set_lives(Integer.valueOf(in.readLine())); //ustawienie ilości żyć gracza 1
						break;
					}
				}
				
				while (!Thread.currentThread().isInterrupted()) {
					if (in.ready()) {
						serverGameState.set_player_2_fire_status(Boolean.parseBoolean(in.readLine())); //ustawienie statusu strzału gracza drugiego
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


