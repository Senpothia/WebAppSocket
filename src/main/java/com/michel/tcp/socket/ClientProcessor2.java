package com.michel.tcp.socket;

import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.PrintWriter;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import org.attoparser.trace.TraceBuilderMarkupHandler;

import com.michel.tcp.Connexion;
import com.michel.tcp.Imei;
import com.michel.tcp.Transfert;
import com.michel.tcp.WebAppSocketApplication;

import java.io.InputStreamReader;
import java.io.BufferedReader;

public class ClientProcessor2 implements Runnable {

	private Socket mySocket;
	private PrintWriter writer = null;
	private BufferedInputStream reader = null;
	private String IMEI = "";
	private String IMSI = "";
	private Connexion connexion;

	public ClientProcessor2(Socket socket, Connexion connexion) {
		this.mySocket = socket;
		this.connexion = connexion;
	}

	// Le traitement
	public void run() {

		System.out.println("INFO$: Lancement du traitement des transferts vers le client");
		boolean closeConnexion = false;
		

		while (!mySocket.isClosed()) {

			try {
				writer = new PrintWriter(mySocket.getOutputStream());
				InputStreamReader inr = new InputStreamReader(mySocket.getInputStream());
				BufferedReader br = new BufferedReader(inr);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (WebAppSocketApplication.buffer.isChange()) {

				writer.println(WebAppSocketApplication.buffer.getCode());
				writer.flush();
				WebAppSocketApplication.buffer.setChange(false);

			}

			if (WebAppSocketApplication.chaine.isChange()) {

				writer.println(WebAppSocketApplication.chaine.getMessage());
				writer.flush();
				WebAppSocketApplication.chaine.setChange(false);
				WebAppSocketApplication.chaine.setLecture(false);
			}

		}

		// Tant que la connexion est active

	}

}
