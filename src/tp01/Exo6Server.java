package tp01;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Exo6Server {

	public static void main(String[] args) {
		try {
			InetAddress adresse = InetAddress.getByName(args[0]);
			int port = Integer.valueOf(args[1]);
			new Exo6Server(adresse, port);
		} catch (IOException e) {
			System.err.println("Arguments manquant(s) : <IP> et/ou <PORT>");
			System.exit(1);
		}
	}

	private MulticastSocket multiCastSocket;

	public Exo6Server(InetAddress adresse, int port) throws IOException {
		multiCastSocket = new MulticastSocket(port);
		multiCastSocket.joinGroup(adresse);
		go();
	}

	private void go() {
		DatagramPacket message;
		byte[] contenuMessage;
		String texte;
		ByteArrayInputStream lecteur;

		while (true) {
			contenuMessage = new byte[1024];
			message = new DatagramPacket(contenuMessage, contenuMessage.length);
			try {
				multiCastSocket.receive(message);
				texte = (new DataInputStream(new ByteArrayInputStream(contenuMessage))).readUTF();
				System.out.println(texte);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}
