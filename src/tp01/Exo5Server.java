package tp01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Exo5Server {
	
	private DatagramSocket dgSocket;

	Exo5Server(int pSrv) throws IOException {
		dgSocket = new DatagramSocket(pSrv);
	}

	void go() throws IOException {
		DatagramPacket dgPacket = new DatagramPacket(new byte[500], 500);
		String str;

		while (true) {
			dgSocket.receive(dgPacket);
			byte[] buffer = new byte[500];
			buffer = dgPacket.getData();
			String reception = new String(buffer, 0, dgPacket.getLength());
			str = multiPhrase(reception);

			dgPacket.setSocketAddress(dgPacket.getSocketAddress());
			byte[] bufDate = str.getBytes();
			dgPacket.setData(bufDate, 0, bufDate.length);

			dgSocket.send(dgPacket);
		}
	}

	public static void main(String[] args) throws IOException {
		/*
		 * Creation d'un datagram socket sur le port spécifié
		 */
		final int DEFAULT_PORT = 9999;
		new Exo5Server( args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[0]) ).go();
	}
	
	private String multiPhrase(String phrase) {
		String nbreStr = phrase.split(":")[0];
		int nbre;
		try {
			nbre = Integer.valueOf(nbreStr);
		} catch (NumberFormatException e) {
			return "1Erreur : multiplicateur manquant.";
		}
		phrase = phrase.split(":")[1];
		String[] mots = phrase.split(" ");
		String res = "";
		for(String s : mots) {
			for(int i = 0 ; i < nbre ; i++)
				res += s + " ";
		}
		return "0" + res;
	}
	

}
