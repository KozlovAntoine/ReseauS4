package tp02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurChargen {
	
	private static final String ASCII = "\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}";
	public static final int PORT_SERVICE = 9876;
	private ServerSocket serverSocket;
	
	
	public static void main(String[] args) {
		try {
			new ServeurChargen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ServeurChargen() throws IOException {
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
			
			int index = 0;
			
			while(client.isConnected()) {
				System.out.println("Envoie : " + getCharToSend(index));
				pw.print(getCharToSend(index++));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pw.flush();
			}
			
			try {
				client.close();
				System.out.println("Connection closed from client.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private char getCharToSend(int index) {
		return ASCII.charAt(index % ASCII.length());
	}
}
