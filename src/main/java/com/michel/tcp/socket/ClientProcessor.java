package com.michel.tcp.socket;

import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.PrintWriter;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.SocketException;

import com.michel.tcp.Imei;
import com.michel.tcp.WebAppSocketApplication;

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
				debug = "                  ----------------------------                      \n";
				debug += "Thread : " + Thread.currentThread().getName() + "\n";
				debug += "Demande de l'adresse : " + remote.getAddress().getHostAddress() + "\n";
				debug += " Sur le port : " + remote.getPort() + "\n";
				debug += "Commande reçue : " + response + "\n";
				debug += "                 ----------------------------                      \n";
				System.err.println("\n" + debug);

				// On traite la demande du client et on lui repond
				String toSend = "";

				switch (response.toUpperCase()) {
				case "A":

					toSend = "SERVEUR$: Vous m'avez envoyer la 1ere lettre de l'alphabet!";
					break;
				case "CLOSE":
					toSend = "SERVEUR$: Vous voulez partir donc! D'accord, bye bye!";
					closeConnexion = true;
					break;
				default: {
					if (response.toUpperCase().startsWith("C:<") && response.endsWith(">")) {

						try {
							String sub = response.substring(3);
							// System.out.println("substring: " + sub);

							String recu = sub.replace('<', ' ');
							// System.out.println("chaine modifiée: " + recu);

							String[] strings = recu.split(" ");
							String IMEI = strings[0];
							IMEI = IMEI.replace(">", "");

							String IMSI = strings[1];
							IMSI = IMSI.replace(">", "");

							int ei = Integer.parseInt(IMEI);
							int si = Integer.parseInt(IMSI);

							System.out.println("SERVEUR$: IMEI = " + IMEI);
							System.out.println("SERVEUR$: IMSI = " + IMSI);
							/*
							 * System.out.println("SERVEUR$: decimal IMEI = " + ei);
							 * System.out.println("SERVEUR$: decimal IMSI = " + si);
							 */

							boolean match = false;
							int j = 0;

							while (!match && j < WebAppSocketApplication.abonnes.size()) {

								Imei i = WebAppSocketApplication.abonnes.get(j);
								int c = i.getCode();
								if (c == ei) {

									toSend = "SERVEUR$: OK";
									match = true;

								}

								j++;
							}

							if (!match) {

								System.out.println("Aucun code enregistré trouvé!");
								toSend = "SERVEUR$: ERROR";
							}

						} catch (Exception e) {

							toSend = "SERVEUR$: Syntax Error !";

						}

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

			System.out.println("********************************************************************\n");
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
