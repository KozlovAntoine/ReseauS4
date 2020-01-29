package tp01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientUdp {

	public static void main(String[] args) {
		new ClientUdp("AAA", 9999);
	}

	private String name;
	private final int port;

	public ClientUdp(String name, int port) {
		this.name = name;
		this.port = port;
		go();
	}

	private void go() {
		String envoi = name;
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
			System.out.println(envoi + " a reçu une réponse du serveur : ");
			System.out.println(new String(packet2.getData()));

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
