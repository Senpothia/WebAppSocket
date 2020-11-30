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
	public static Buffer buffer = new Buffer();
	public static Chaine chaine = new Chaine();

	public static void main(String[] args) {
		SpringApplication.run(WebAppSocketApplication.class, args);

	
		int port = 5725;
		long codeInit1 = 867420040418313L;
		Imei imeiInit1 = new Imei(codeInit1);
		abonnes.add(imeiInit1);
		
		long codeInit2 = 867420040421598L;
		Imei imeiInit2 = new Imei(codeInit2);
		abonnes.add(imeiInit2);
		
		long codeInit3 = 15L;
		Imei imeiInit3 = new Imei(codeInit3);
		abonnes.add(imeiInit3);
		
		
		ServerTcp ts = new ServerTcp(port, 100);
		ts.open();

		System.out.println("INFO$: Serveur initialisé.");

	}

}
