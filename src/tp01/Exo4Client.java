package tp01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Exo4Client {
	
	public static void main(String[] args) {
		new Exo4Client("A", 9999);
	}
	
	private String name;
	private final int port;

	public Exo4Client(String name, int port) {
		this.name = name;
		this.port = port;
		go();
	}

	private void go() {
		String envoi = " ";
		
		while(!envoi.isEmpty()) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Entrer une phrase : (Vide => exit)");
			envoi = sc.nextLine();
			if(!envoi.isEmpty()) {
				byte[] buffer = envoi.getBytes();

				try {
					// On initialise la connexion côté client
					DatagramSocket client = new DatagramSocket();

					// On crée notre datagramme
					InetAddress adresse = InetAddress.getByName("127.0.0.1");
					DatagramPacket packet = new DatagramPacket(buffer, buffer.length, adresse, port);

					// On lui affecte les données à envoyer
					packet.setData(buffer);

					// On envoie au serveur
					client.send(packet);

					// Et on récupère la réponse du serveur
					byte[] buffer2 = new byte[8196];
					DatagramPacket packet2 = new DatagramPacket(buffer2, buffer2.length, adresse, port);
					client.receive(packet2);
					System.out.println(traitement(new String(packet2.getData())));

				} catch (SocketException e) {
					e.printStackTrace();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private String traitement(String s) {
		return s.substring(1, s.length());
	}

}
