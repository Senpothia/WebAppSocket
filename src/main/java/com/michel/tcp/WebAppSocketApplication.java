package com.michel.tcp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.michel.tcp.socket.Server;
import com.michel.tcp.socket.ServerTcp;

@SpringBootApplication
public class WebAppSocketApplication {

	public static List<Imei> abonnes = new ArrayList<Imei>();
	public static List<Connexion> connexions = new ArrayList<Connexion>();

	public static void main(String[] args) {
		SpringApplication.run(WebAppSocketApplication.class, args);

		/*
		String ip = "35.180.165.19";
		Imei imei = new Imei(125463);
		LocalDateTime date = LocalDateTime.now();
		boolean autorisation = true;
		
		Connexion connexion =  new Connexion(imei, ip, date, autorisation);
		WebAppSocketApplication.connexions.add(connexion);
		 */
		
		int port = 5725;

		ServerTcp ts = new ServerTcp(port, 100);
		ts.open();

		System.out.println("INFO$: Serveur initialisé.");

	}

}
