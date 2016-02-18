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
	private String host;
	public ClientTCP() {
		// TODO Auto-generated constructor stub
		clientGameState = (ClientGameState) ClassesInstances.clientGameState;
	}
	public ClientTCP(String connection_adress) {
		// TODO Auto-generated constructor stub
		clientGameState = (ClientGameState) ClassesInstances.clientGameState;
		this.host=connection_adress;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket(InetAddress.getByName(host), 4012);
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
					while (!Thread.currentThread().isInterrupted()) {
						if (in.ready()) {
							clientGameState.set_mouse_first_player_x(Integer.valueOf(in.readLine()));
							break;
						}
					}
					while (!Thread.currentThread().isInterrupted()) {
						if (in.ready()) {
							clientGameState.set_mouse_first_player_y(Integer.valueOf(in.readLine()));
							break;
						}
					}
					while (!Thread.currentThread().isInterrupted()) {
						if (in.ready()) {
							clientGameState.set_first_player_picture(in.readLine());
							break;
						}
					}
					
					while (!Thread.currentThread().isInterrupted()) {
						if (in.ready()) {
							clientGameState.set_posx(Integer.valueOf(in.readLine()));
							break;
						}
					}
					while (!Thread.currentThread().isInterrupted()) {
						if (in.ready()) {
							clientGameState.set_posy(Integer.valueOf(in.readLine()));
							break;
						}
					}
					
					while (!Thread.currentThread().isInterrupted()) {
						if (in.ready()) {
							clientGameState.set_pos_player_x(Integer.valueOf(in.readLine()));
							break;
						}
					}
					
					while (!Thread.currentThread().isInterrupted()) {
						if (in.ready()) {
							clientGameState.set_pos_player_y(Integer.valueOf(in.readLine()));
							break;
						}
					}
					
					while (!Thread.currentThread().isInterrupted()) {
						if (in.ready()) {
							clientGameState.set_lives_second(Integer.valueOf(in.readLine()));
							break;
						}
					}
					
					while (!Thread.currentThread().isInterrupted()) {
						if (in.ready()) {
							clientGameState.set_first_player_currently_choosed_weapon(Integer.valueOf(in.readLine()));
							break;
						}
					}
					
					while (!Thread.currentThread().isInterrupted()) {
						if (in.ready()) {
							clientGameState.set_player_1_fire_status(Boolean.parseBoolean(in.readLine()));
							break;
						}
					}
					while (!Thread.currentThread().isInterrupted()) {
						if (in.ready()) {
							clientGameState.set_second_player_fire_render_status(Boolean.parseBoolean(in.readLine()));
							break;
						}
					}
					
					out.println(String.valueOf(clientGameState.get_player_2_x_position()));
					out.println(String.valueOf(clientGameState.get_player_2_y_position()));
					out.println(String.valueOf(clientGameState.get_mouse_second_player_x()));
					out.println(String.valueOf(clientGameState.get_mouse_second_player_y()));
					out.println(String.valueOf(clientGameState.get_second_player_picture()));
					
					out.println(String.valueOf(clientGameState.get_second_posx()));
					out.println(String.valueOf(clientGameState.get_second_posy()));
					out.println(String.valueOf(clientGameState.get_second_pos_player_x()));
					out.println(String.valueOf(clientGameState.get_second_pos_player_y()));
					
					out.println(String.valueOf(clientGameState.get_lives()));
					
					out.println(String.valueOf(clientGameState.get_second_player_currently_choosed_weapon()));
					
					out.println(String.valueOf(clientGameState.get_player_2_fire_status()));
					out.println(String.valueOf(clientGameState.get_first_player_fire_render_status()));
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
