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

public class SerwerThread implements Runnable {
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
			String zmienna[] = new String[24];
			zmienna=wczytaj();
			PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
			String odp[]=new String[4];
			out.println("##Klient ANSWEAR## Nawiązano połaczenie");
			while(true)
				{
					if(in.ready())
					{
						identifier=in.readLine();
						break;
					}
				}
			
			for(int j=0;j<liczba_pytan;++j) //ten fragment odpowiada za zadanie 4 pyta�
			{
				for(int i=0;i<6;++i) //ten fragment odpowiada za przeczytanie 6 linijek
				{
					if (Thread.currentThread().isInterrupted()) return;
					if(i==5)//gdy wypisze wszystkie linijki to wchodzi do p�tli
					{
						while(true)
							{
								if(in.ready()==true)//kiedy wej�cie jest gotowe na przyj�cie wiadomo�ci to wyonuje si� poni�szy kod
								{
									odp[j]=in.readLine();//do listy odpowiedi jest przypisywana aktualna odpowied�
									if(zmienna[i+j*6].equals(odp[j])) //je�eli odebrana wiadomo�� r�wna si� odpowiedzi to wypisywany jest napis dobrze lub nie dobrze
									{
										System.out.println("DOBRZE");
										++liczba_punktow;	
									}
									else
									{
										System.out.println("zle");
									}
									break;
								}
							}
					}
					else
					out.println(zmienna[i+j*6]);//dop�ki nie wypisze 5 pyta� wykonuje si� tylko ten kod
				}
			}
			
			zapisz("/temp/Results.txt","NUMER IDENTYFIKATORA "+identifier+"  liczba punktow to  " +liczba_punktow);
			
			zapisz("/temp/Answears.txt","Numer indeksu "+identifier+" "+odp[0]+" "+odp[1]+" "+odp[2]+" "+odp[3]);
			
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
	private String[] wczytaj() throws IOException
	{
		FileInputStream f = new FileInputStream("temp/Questions.txt");
		DataInputStream in = new DataInputStream(f);
		BufferedReader r = new BufferedReader(new InputStreamReader(in));
		String strLine;
		String dane[]=new String[24];
		int i=0;
		//******************************************
		if((strLine = r.readLine()) != null)
		{
			liczba_pytan=Integer.valueOf(strLine);
		}
		while ((strLine = r.readLine()) != null)
		{			
		    dane[i] = strLine;
		    i++;
		}
		in.close();			
		return dane;
	}
	private void  zapisz(String plik,String tekst) throws IOException
	{
		FileWriter s = new FileWriter(plik,true);
		BufferedWriter out = new BufferedWriter(s);		
		out.write(tekst);
		out.newLine();
		out.close();
	}
	
}
