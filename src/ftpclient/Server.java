package ftpclient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Server {
	
	private String ip;
	private int port;
	private SocketChannel canal;
	
	public Server() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("IP du serveur :");
		String ip = sc.nextLine();
		int port = 0;
		while(port < 1) {
			System.out.println("Port du serveur :");
			port = parsePort(sc.nextLine());
		}
		
		this.ip = ip;
		this.port = port;
	}
	
	public Server(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	
	public static int parsePort(String port) {
		try {
			return Integer.valueOf(port);
		} catch (NumberFormatException e) {
			System.err.println("Le port spécifié n'est pas correct.");
			return -1;
		}
	}
	
	public void connect(User user) {
		createSocket();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		String msg = "LIST";
		try {
			canal.write(ByteBuffer.wrap(msg.getBytes()));
			System.out.println("Message envoyé");
			while(canal.read(buffer) == 0) {}
			String s = new String(buffer.array());
			System.out.print("Recu : " + s);
		} catch (IOException e) {
			System.err.println("Erreur lors de la transmission du message vers le serveur.");
		}
	}
	
	private void createSocket() {
		SocketAddress remote = new InetSocketAddress(ip,port);
		try {
			canal = SocketChannel.open();
			canal.configureBlocking(false);
			canal.connect(remote);
			while(!canal.finishConnect()) {}
			System.out.println("Connection établie avec le serveur.");
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while(canal.read(buffer) == 0) {}
			String s = new String(buffer.array());
			System.out.print("Serveur : " + s);
		} catch(IOException e) {
			System.err.println("Erreur lors de la connection au serveur.");
			System.exit(1);
		}
	}

}
