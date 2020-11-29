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

import com.michel.tcp.Connexion;
import com.michel.tcp.Imei;
import com.michel.tcp.WebAppSocketApplication;

import java.io.InputStreamReader;
import java.io.BufferedReader;

public class ClientProcessor implements Runnable, Observer {

	private Socket mySocket;
	private PrintWriter writer = null;
	private BufferedInputStream reader = null;
	private String IMEI = "";
	private String IMSI = "";
	private Connexion connexion;

	public ClientProcessor(Socket socket, Connexion connexion) {
		this.mySocket = socket;
		this.connexion = connexion;
	}

	// Le traitement
	public void run() {
		System.out.println("INFO$: Lancement du traitement de la connexion d'un client");
		boolean closeConnexion = false;

		// Tant que la connexion est active
		while (!mySocket.isClosed()) {
			try {
				writer = new PrintWriter(mySocket.getOutputStream());
				InputStreamReader inr = new InputStreamReader(mySocket.getInputStream());
				BufferedReader br = new BufferedReader(inr);
				
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
				debug += "Date de réception: " + LocalDateTime.now() + "\n";

				System.err.println("\n" + debug);

				// On traite la demande du client et on lui repond
				String toSend = "";

				
				try {

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
								
								String recu = sub.replace('<', ' ');
							

								String[] strings = recu.split(" ");
								String IMEI = strings[0];
								IMEI = IMEI.replace(">", "");

								String IMSI = strings[1];
								IMSI = IMSI.replace(">", "");

								long ei = Long.parseLong(IMEI);
								long si = Long.parseLong(IMSI);

								Imei imei = new Imei(ei);
								connexion.setImei(imei);

								System.out.println("SERVEUR$: IMEI = " + IMEI);
								System.out.println("SERVEUR$: IMSI = " + IMSI);
								

								boolean match = false;
								int j = 0;

								while (!match && j < WebAppSocketApplication.abonnes.size()) {

									Imei i = WebAppSocketApplication.abonnes.get(j);
									long c = i.getCode();
									if (c == ei) {

										toSend = "OK";
										match = true;
										connexion.setAutorisation(true);

									}

									j++;
								}

								if (!match) {

									System.out.println("Aucun code enregistré trouvé!");
									toSend = "ERROR";
									connexion.setAutorisation(false);
								}

							} catch (Exception e) {

								toSend = "Syntax Error !";

							}

							WebAppSocketApplication.connexions.add(connexion);

						} else {

							toSend = "Syntax Error !";
							// toSend = String.valueOf(findSum(response));
						}

						break;
					}

					}

				} catch (Exception e) {

					WebAppSocketApplication.connexions.remove(connexion);
					mySocket.close();
				}
				
				System.out.println("INFO$: toSend = " + toSend);
				// On envoie la reponse au client
				writer.println(toSend);
				writer.flush();

				if (closeConnexion) {
					System.err.println("INFO$: COMMANDE CLOSE DETECTEE!");
					writer = null;
					reader = null;
					mySocket.close();
					WebAppSocketApplication.connexions.remove(connexion);
					break;
				}
				
			} catch (SocketException e) {
				System.err.println("INFO$: LA CONNEXION A ETE INTERROMPUE !");
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("********************************************************************\n");
			
		}  // fin while 

		System.out.println("Sortie");
		WebAppSocketApplication.connexions.remove(connexion);
		// return;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
