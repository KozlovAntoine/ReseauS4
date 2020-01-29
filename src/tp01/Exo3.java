package tp01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Exo3 {
	private DatagramSocket dgSocket;

	Exo3(int pSrv) throws IOException {
		dgSocket = new DatagramSocket(pSrv);
	}

	void go() throws IOException {
		DatagramPacket dgPacket = new DatagramPacket(new byte[0], 0);
		String str;

		while (true) {
			dgSocket.receive(dgPacket);
			System.out.println("Datagram received from " + dgPacket.getSocketAddress());

			dgPacket.setSocketAddress(dgPacket.getSocketAddress());
			str = new java.util.Date().toString() + "\n";
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
		new Exo3( args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[0]) ).go();
	}
}