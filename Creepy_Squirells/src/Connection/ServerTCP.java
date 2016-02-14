package Connection;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ServerTCP extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int licznik_klientow = 0; // licznik klient�w na pocz�tku wyzerowany
		int port = 4012; // port pod kt�rym stawiamy serwer

		try {
			Executor exec = Executors.newFixedThreadPool(15); // dodanie kolejki
																// w�tk�w o
																// rozmiarze 1
			ServerSocket serverSocket = new ServerSocket(port); // uruchomienie
																// gniazdka
																// sieciowego
																// pod
																// odpowiednim
																// portem
			Scanner klawiatura = new Scanner(System.in); // urucomienie
			do {
				++licznik_klientow; // liczy uruchomione klient�w
				Socket clientSocket = serverSocket.accept(); //

				if (clientSocket.isConnected() == true) {
					exec.execute(new SerwerThread(clientSocket));
				}

				if (licznik_klientow > 15)
					break;
			} while (true);
			// ********************************************

			serverSocket.close();
			klawiatura.close();

		} 
		
		catch (Exception e) {
			System.err.println(e);
		}

		super.run();
	}

}