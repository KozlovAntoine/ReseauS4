package tp02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServeurDaytime {
	
	public static final int PORT_SERVICE = 9876;
	private ServerSocket serverSocket;
	
	public static void main(String[] args) {
		try {
			new ServeurDaytime();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ServeurDaytime() throws IOException {
		serverSocket = new ServerSocket(PORT_SERVICE);
		this.attenteClient();
	}
	
	private void attenteClient() {
		Socket client = null;
		PrintWriter pw = null;
		while(true) {
			System.out.println("Waiting client to connect.");
			try {
				client = serverSocket.accept();
				System.out.println("Connection from client.");
				pw = new PrintWriter(client.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.println(new Date().toString());
			pw.flush();
			
			try {
				client.close();
				System.out.println("Connection closed from client.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
