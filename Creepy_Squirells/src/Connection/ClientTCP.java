package Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class ClientTCP extends Thread {

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
			System.out.println("##SERWER INFO## \nOCZEKIWANIE NA PO��CZENIE");
			while (true) {
				if(Thread.currentThread().isInterrupted())
				{
					socket.close();
					klawiatura.close();
					return;
				}
				if (in.ready() == true) {
					System.out.println(in.readLine());
					break;
				}
			}

			System.out.print("\nPodaj swoj identyfikator\n");
			out.println(klawiatura.nextLine());
			String tekst = "";
			while (true) {
				
				if(Thread.currentThread().isInterrupted())
				{
					socket.close();
					klawiatura.close();
					return;
				}
				if (in.ready() == true) {
					tekst = in.readLine();
					System.out.println(tekst);
					i++;
					if (i == 5) {

						out.println(klawiatura.nextLine());
						i = 0;
					}
				}
				sleep(200);

				if (tekst.equals("koniec")) {
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
