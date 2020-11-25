package com.michel.tcp.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;


public class ServerTcp {
	
	private int port = 5725;
	private int maxSockets = 100;
	private ServerSocket server = null;
	private boolean isRunning = true;

	public ServerTcp() {
		try{
			server = new ServerSocket(port, maxSockets);

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public ServerTcp(int bindPort, int maxClientConnexions){
		this.port = bindPort;
		this.maxSockets = maxClientConnexions;
		try{
			server = new ServerSocket(this.port, this.maxSockets);

		}catch(IOException e){
			e.printStackTrace();
		}
	}


	//Pour lancer le serveur
	public void open(){
		//On lance le serveur sur un thread à part car il a une boucle infine
		Thread serverThread = new Thread(new Runnable(){
			public void run(){
				while(isRunning == true){
					try{
						//On attend une connexion d'un client
						Socket clientSocket = server.accept();

						//Une fois reçu, on traite l'echange avec ce nouveau client dans un nouveau thread
						System.out.println("INFO$: Une nouvelle connexion d'un client reçue!");
						Thread newClientThread = new Thread(new ClientProcessor(clientSocket));
						newClientThread.start();
					}catch(IOException e){
						e.printStackTrace();
					}
				}

				//On ferme le secket du serveur
				try{
					server.close();
				}catch(IOException e){
					e.printStackTrace();
					server = null;
				}
			}
		});

		serverThread.start();
	}

	//Pour arreter le serveur
	public void close(){
		this.isRunning = false;
	}
	
	

}
