package com.michel.tcp.socket;

import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.PrintWriter;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class ClientProcessor implements Runnable {

	private Socket mySocket;
	private PrintWriter writer = null;
	private BufferedInputStream reader = null;
	private String IMEI = "";
	private String IMSI = "";

	public ClientProcessor(Socket socket) {
		this.mySocket = socket;
	}

	// Le traitement
	public void run() {
		System.out.println("INFO$: Lancement du traitement de la connexion d'un client");
		boolean closeConnexion = false;

		// Tanque la connexion est active
		while (!mySocket.isClosed()) {
			try {
				writer = new PrintWriter(mySocket.getOutputStream());
				InputStreamReader inr = new InputStreamReader(mySocket.getInputStream());
				BufferedReader br = new BufferedReader(inr);
				// writer = new PrintWriter(mySocket.getOutputStream());
				// reader = new BufferedInputStream(mySocket.getInputStream());

				// On attend la demande du client
				// String response = read();
				String response = br.readLine();
				System.out.println("INFO$: Message reçu du client: " + response);
				
				// On affiche quelques infos, pour le débuggage
				InetSocketAddress remote = (InetSocketAddress) mySocket.getRemoteSocketAddress();
				String debug = "";
				debug = "Thread : " + Thread.currentThread().getName() + ". ";
				debug += "Demande de l'adresse : " + remote.getAddress().getHostAddress() + ".";
				debug += " Sur le port : " + remote.getPort() + ".\n";
				debug += "\t -> Commande reçue : " + response + "\n";
				System.err.println("\n" + debug);

				// On traite la demande du client et on lui repond
				String toSend = "";

				switch (response.toUpperCase()) {
				case "A":
					System.out.println("case A");
					toSend = "SERVEUR$: Vous m'avez envoyer la 1ere lettre de l'alphabet!";
					break;
				case "CLOSE":
					toSend = "SERVEUR$: Vous voulez partir donc! D'accord, bye bye!";
					closeConnexion = true;
					break;
				default: {
					if (response.toUpperCase().startsWith("C:<")) {
						String Recu = response.substring(2).replace('<', ' ');
						Recu = Recu.replace('>', ' ');
						String[] strings = Recu.split("  ");
						IMEI = strings[0];
						IMSI = strings[1];

						System.out.println("SERVEUR$: IMEI = " + IMEI);
						System.out.println("SERVEUR$: IMSI = " + IMSI);

						toSend = "SERVEUR$: OK";

					} else {

						toSend = "SERVEUR$: Syntax Error !";
						// toSend = String.valueOf(findSum(response));
					}

					break;
				}

				}

				System.out.println("INFO$: toSend = " + toSend);
				// On envoie la reponse au client
				writer.println(toSend);
				writer.flush();

				if (closeConnexion) {
					System.err.println("INFO$: COMMANDE CLOSE DETECTEE! ");
					writer = null;
					reader = null;
					mySocket.close();
					break;
				}
			} catch (SocketException e) {
				System.err.println("INFO$: LA CONNEXION A ETE INTERROMPUE ! ");
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	// La methode que nous utilisons pour lire la reponse du client
	/*
	 * private String read() throws IOException{ String response = ""; int stream;
	 * byte[] b = new byte[4096]; stream = reader.read(b); response = new String (b,
	 * 0, stream); return response; }
	 */

	public int findSum(String str) {
		// A temporary string
		String temp = "0";

		// holds sum of all numbers present in the string
		int sum = 0;

		// read each character in input string
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			// if current character is a digit
			if (Character.isDigit(ch))
				temp += ch;

			// if current character is an alphabet
			else {
				// increment sum by number found earlier
				// (if any)
				sum += Integer.parseInt(temp);

				// reset temporary string to empty
				temp = "0";
			}
		}

		// atoi(temp.c_str()) takes care of trailing
		// numbers
		return sum + Integer.parseInt(temp);
	}

}
