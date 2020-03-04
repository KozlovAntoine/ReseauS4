package tp02;

import java.io.*;
import java.net.*;

public class ExempleServer {

	public static final int PORT_SERVICE = 9876;
	private ServerSocket s_Srv;

	ExempleServer() throws IOException {
		s_Srv = new ServerSocket(PORT_SERVICE);
	}

	// Réception clients et transfert vers un thread dédié
	private void attenteClient() throws IOException {
		Socket s_Clt;
		while (true) {
			s_Clt = s_Srv.accept();
			new ReponseTruc(s_Clt).start();
		}
	}

	// Test d'usage de la classe ... et rien d'autre
	public static void main(String[] args) throws IOException {
		ExempleServer srvTruc = new ExempleServer();
		srvTruc.attenteClient();
	}

	/**
	 * Gestion du protocole du service <<Truc>>
	 **/
	class ReponseTruc extends Thread {
		private Socket s_Client;

		ReponseTruc(Socket sClient) {
			this.s_Client = sClient;
			// Init des flots de données client ici
		}

		void dialogue() {
			/* le dialogue client/serveur ici */ }

		public void run() {
			dialogue();

			try {
				s_Client.close();
			} catch (IOException e) {
			}
		}
	}
}