package Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

import org.newdawn.slick.state.GameState;

import Core.ClassesInstances;
import States.ClientGameState;

public class ClientTCP extends Thread {
	private ClientGameState clientGameState;
	
	public ClientTCP() {
		// TODO Auto-generated constructor stub
		clientGameState = (ClientGameState) ClassesInstances.clientGameState;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket(InetAddress.getByName("25.149.245.97"), 4012);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // zwraca
																				// strumie�
																				// wyj�ciowy
																				// dla
																				// gniazdka
																				// klienta
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));// tworzy
																									// nowy
																									// strumie�
																									// wej�ciowy
			int i = 0;
			Scanner klawiatura = new Scanner(System.in);
			System.out.println("##KLIENT INFO## \nOCZEKIWANIE NA POŁĄCZENIE");
			while (true) {
				if(Thread.currentThread().isInterrupted())
				{
					socket.close();
					klawiatura.close();
					return;
				}
				if (in.ready() == true) {
					System.out.println(in.readLine()); //pierwsze przyjęcie
					break;
				}
			}

			out.println("##CLIENT MESSAGE## Nawiązano połaczenie z serwerem");//pierwsze wysłanie
			
			String tekst = "";
			while (true) {

				if (Thread.currentThread().isInterrupted()) {
					socket.close();
					klawiatura.close();
					return;
				}

				while (!Thread.currentThread().isInterrupted()) {
					while (!Thread.currentThread().isInterrupted()) {
						if (in.ready()) {
							clientGameState.set_player_1_x_position(Integer.valueOf(in.readLine()));
							break;
						}
					}
					while (!Thread.currentThread().isInterrupted()) {
						if (in.ready()) {
							clientGameState.set_player_1_y_position(Integer.valueOf(in.readLine()));
							break;
						}
					}
					out.println(String.valueOf(clientGameState.get_player_2_x_position()));
					out.println(String.valueOf(clientGameState.get_player_2_y_position()));
				}

				if (tekst.equals("koniec")) {
					System.out.println("Zakończenie działania klienta");
					out.println("koniec");
					break;
				}

			}

			socket.close();
			klawiatura.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		super.run();
	}

}
