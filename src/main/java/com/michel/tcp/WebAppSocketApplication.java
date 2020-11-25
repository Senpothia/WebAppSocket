package com.michel.tcp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.michel.tcp.socket.Server;
import com.michel.tcp.socket.ServerTcp;

@SpringBootApplication
public class WebAppSocketApplication {

	public static List<Imei> abonnes = new ArrayList<Imei>();
	
	public static void main(String[] args) {
		SpringApplication.run(WebAppSocketApplication.class, args);
		
		
		/*
		 * 
		 *   Version initiale
		 *   
		 *   
		String st1 = "aaaa";
		String st2 = "aaaa";
		
		System.out.println("comp1:" + st1.equals(st2));
				
		
		Server server = new Server();
		server.connect();
		
		
		*/
		
		//  Version 2
		
		
		      int port = 5725;
		      
		      ServerTcp ts = new ServerTcp(port, 100);
		      ts.open();
		      
		      System.out.println("INFO$: Serveur initialisé.");
		      
		    
		
		
	}

}
