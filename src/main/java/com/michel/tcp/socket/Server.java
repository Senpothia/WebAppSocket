package com.michel.tcp.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.michel.tcp.Imei;
import com.michel.tcp.WebAppSocketApplication;

public class Server {

	private ServerSocket serverTcp = null;
	private Socket sc = null;
	private DataInputStream in;
	private DataOutputStream out;

	private int port = 5725;

	public void connect() {

		try {
			serverTcp = new ServerSocket(port);
			System.out.println("Serveur lancé!");

			while (true) {

				sc = serverTcp.accept();
				System.out.println("Client connecté!");

				in = new DataInputStream(sc.getInputStream());
				out = new DataOutputStream(sc.getOutputStream());

				String message = in.readUTF();
				System.out.println(message);

				boolean present = false;

				for (Imei i : WebAppSocketApplication.abonnes) {

					System.out.println("Code: " + i.getCode());
					if (message.equals(i.getCode())) {

						out.writeUTF("OK");
						present = true;

					}

				}

				if (!present) {

					out.writeUTF("ERROR");

				}

				sc.close();
				System.out.println("Client déconnecté!");

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	}

}
